import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener {

    private JPanel pn;
    private JPanel pn_2;
    private Timer fadeOutTimer, fadeInTimer;
    private float alpha = 1f;
    
    private String[] randomStrings_1 = {"조만간 죽을거야", "일부러 그러네", "칼로 죽여버릴거야", "쓰레기를 자꾸 입에 넣어"};
    private String[] randomStrings_2 = {"박정숙","김철민","못생긴년","씨발년"};
    private String[] randomStrings_3 = {"앞에 남자가 쳐다봐","방금 옆에 여자가 비웃었다","뭐하냐,,ㅋ"};
    
    
    public MainFrame(String title) {
        setTitle(title);
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBackground(Color.black);
        FirstScreen();
        setFocusable(true);
        setVisible(true);
    }

    public void FirstScreen() {
        pn = new TransparentPanel();
        pn.setLayout(new BorderLayout());
        pn.setBackground(Color.white);

        JLabel lblTitle = new JLabel("행복해야 할 순간 / The Moment When Should Be Happy");
        lblTitle.setFont(new Font("HY신명조", Font.BOLD, 20));
        lblTitle.setBorder(new EmptyBorder(350, 700, 0, 50));  // 상단 50픽셀 마진 추가

        JTextArea tra = new JTextArea();
        tra.setEditable(false);
        tra.setText("<행복해야 할 순간>은 나의 가장 행복했던 기억이 나의 의도와는 "
        		+ "상관없이 욕설과 두려움, 분노로 왜곡되어\n기록되는 경험을 갖는다.\n직접적으로 나의 기억이 변질되는 무력감을 통해\n"
        		+ "과거에서부터 현재 이 순간까지도 행복하게 기록되었어야 할 기억들이 매 순간들이 불안정하게 곡해되는 과정을\n"
        		+ "상상하고 감정적으로 가담할 수 있기를 기원한다");
        tra.setFont(new Font("HY신명조", Font.BOLD, 20));
        tra.setBorder(new EmptyBorder(100, 500, 0, 50));  // 상단 50픽셀 마진 추가

        pn.add(lblTitle, BorderLayout.NORTH);
        pn.add(tra, BorderLayout.CENTER);
        addKeyListener(this);

        add(pn, BorderLayout.CENTER);
    }

    public void SecondScreen() {
    	JPanel pn_2_center = new JPanel();
    	JPanel pn_2_right = new JPanel();
    	JPanel pn_2_left = new JPanel();
    	JPanel pn_2_top = new JPanel();
    	JPanel pn_2_bottom = new JPanel();
    	pn_2_right.setBackground(Color.black);
    	pn_2_left.setBackground(Color.black);
    	pn_2_top.setBackground(Color.black);
    	pn_2_bottom.setBackground(Color.black);
    	pn_2_center.setBackground(Color.black);
    	pn_2_center.setBorder(new EmptyBorder(90, 403, 0, 403));  // 상단 50픽셀 마진 추가

    	pn_2_center.setLayout(new BorderLayout());
    	
        pn_2 = new TransparentPanel();
        pn_2.setLayout(new BorderLayout());
        setBackground(Color.black);
        JPanel pns_2 = new JPanel();
        pns_2.setLayout(new BorderLayout());
        pns_2.setBorder(new EmptyBorder(50, 50, 0, 50));  // 상단 50픽셀 마진 추가
        pns_2.setBackground(Color.white);

//        JTextPane  lblTitle_2 = new JTextPane("나의 가장 행복했던 하루는 어떤 날이었나요.\n\n그날의 어떤 기억들이 나를 행복하게 만들었나요?");
        JTextPane  lblTitle_2 = new JTextPane();
        StyledDocument doc = lblTitle_2.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        
        try {
            doc.insertString(doc.getLength(), "你人生中最幸福的那一天是怎樣的一天呢?\\n 是什麼樣的記憶使你那天覺得幸福呢?", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        doc.setParagraphAttributes(0, doc.getLength(), center, false);        
        lblTitle_2.setFont(new Font("HY신명조", Font.BOLD, 30));
        lblTitle_2.setBorder(new EmptyBorder(150, 100, 0, 50));  // 상단 50픽셀 마진 추가
        
        JTextArea txt_2 = new JTextArea("< 천천히, 여기에 입력해 주세요");
        txt_2.setForeground(Color.GRAY);  // 플레이스홀더 색상 설정
        txt_2.setFont(new Font("바탕", Font.BOLD, 30));        
        txt_2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_2.getText().equals("< 천천히, 여기에 입력해 주세요")) {
                    txt_2.setText("");
                    txt_2.setForeground(Color.BLACK);  // 입력할 때의 색상
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_2.getText().isEmpty()) {
                    txt_2.setForeground(Color.GRAY);
                    txt_2.setText("< 천천히, 여기에 입력해 주세요");
                }
            }
        });
        txt_2.addKeyListener(new KeyAdapter() {
            int lastModifiedIndex = -1;

            @Override
            public void keyReleased(KeyEvent e) {
                StringBuilder textBuilder = new StringBuilder(txt_2.getText());

                for (String particle : new String[]{"은", "는", "이", "가"}) {
                    int index = textBuilder.lastIndexOf(particle);

                    if (index > lastModifiedIndex && index != -1) { 
                        lastModifiedIndex = index + particle.length();  // 최근 변경된 위치 갱신

                        String randomStr = randomStrings_1[new Random().nextInt(randomStrings_1.length)];

                        Timer timer = new Timer(300, new ActionListener() {
                            int charIndex = 0;

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (charIndex < randomStr.length()) {
                                    textBuilder.insert(index + particle.length() + charIndex, randomStr.charAt(charIndex));  // 여기를 수정했습니다.
                                    txt_2.setText(textBuilder.toString());
                                    txt_2.setCaretPosition(textBuilder.length());  // 커서 위치를 맨 끝으로 이동
                                    charIndex++;
                                } else {
                                    ((Timer) e.getSource()).stop();
                                }
                            }
                        });
                        timer.start();

                        break;
                    }
                }
            }
        });
//        pn_2.add(lblTitle_2, BorderLayout.NORTH);
        pns_2.add(txt_2, BorderLayout.NORTH);
        
        
        pn_2_center.add(lblTitle_2, BorderLayout.NORTH);
        pn_2_center.add(pns_2, BorderLayout.CENTER);
        pn_2.add(pn_2_right,BorderLayout.EAST);
        pn_2.add(pn_2_left,BorderLayout.WEST);
        pn_2.add(pn_2_top,BorderLayout.NORTH);
        pn_2.add(pn_2_bottom,BorderLayout.SOUTH);
        pn_2.add(pn_2_center,BorderLayout.CENTER);
        pn_2.setOpaque(false);
        alpha = 0f;  // 초기 투명도 설정
        
        add(pn_2, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Fade in animation
        fadeInTimer = new Timer(15, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.02f;
                if (alpha >= 1f) {
                    alpha = 1f;
                    fadeInTimer.stop();
                }
                pn_2.repaint();
            }
        });
        fadeInTimer.start();
    }
    
    class TransparentPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            super.paintComponent(g2d);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Fade out animation for pn
            fadeOutTimer = new Timer(15, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    alpha -= 0.02f;
                    if (alpha <= 0f) {
                        alpha = 0f;
                        fadeOutTimer.stop();
                        remove(pn);
                        SecondScreen();
                    }
                    pn.repaint();
                }
            });
            fadeOutTimer.start();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
