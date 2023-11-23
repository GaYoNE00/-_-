import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class MainFrame extends JFrame implements KeyListener {

    private JPanel pn;
    private JPanel pn_2;
    private Timer fadeOutTimer, fadeInTimer;
    private float alpha = 1f;
    private JTextArea txt_2;

    private int currentScreen = 1; // 현재 화면을 추적하는 변수
    // 대만어 트리거 단어와 중국어 문장 배열
    private String[] triggerWords = {"媽媽", "爸爸", "母親", "父親", "哥哥", "弟弟", "兄弟", "姐妹", "姐姐", "妹妹", "家庭", "朋友", "熟人", "我", "我的"};
    private String[] randomChineseStrings = {"遲早會死的", "前面那個男人在看著我", "蔡秀琴，王志明", "賤人", "一直把垃圾塞進嘴裡", "剛剛旁邊那女人在嘲笑我", "醜男", "醜女", "用刀殺掉他吧", "你是故意的吧", "想死", "好髒"};
    private String text;
    private ArrayList<String> triggerList = new ArrayList<String>();

    public MainFrame(String title) {
        setTitle(title);
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setBackground(Color.black);
        FirstScreen();
        setFocusable(true);
        addKeyListener(this);
        setVisible(true);
    }
    public static void main(String[] args) {
        MainFrame mf = new MainFrame("Korea");
    }
    public void FirstScreen() {
        currentScreen = 1; // 첫 번째 화면으로 설정
        pn = new TransparentPanel();
        pn.setLayout(new BorderLayout());
        pn.setBackground(Color.white);

        JLabel lblTitle = new JLabel("你應該要快樂的時刻 / The Moment When Should Be Happy");
        lblTitle.setFont(new Font("HY신명조", Font.BOLD, 20));
        lblTitle.setBorder(new EmptyBorder(350, 700, 0, 50));

        JTextArea tra = new JTextArea();
        tra.setEditable(false);
        tra.setText("你應該要快樂的時刻>代表了即使是我最快樂的記憶，也可能會因為罵人的話、恐懼和憤怒而被歪曲\n這是一種直接的無力感，我的記憶被扭曲了\n我希望你能夠想像並且情感上參與這個過程，記憶中原本應該是快樂的時刻變得不穩定和曲解");
        tra.setFont(new Font("HY신명조", Font.BOLD, 20));
        tra.setBorder(new EmptyBorder(100, 500, 0, 50));

        pn.add(lblTitle, BorderLayout.NORTH);
        pn.add(tra, BorderLayout.CENTER);

        add(pn, BorderLayout.CENTER);
    }

    public void SecondScreen() {
        currentScreen = 2; // 두 번째 화면으로 설정
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
        pn_2_center.setBorder(new EmptyBorder(90, 403, 0, 403));

        pn_2_center.setLayout(new BorderLayout());

        pn_2 = new TransparentPanel();
        pn_2.setLayout(new BorderLayout());
        setBackground(Color.black);
        JPanel pns_2 = new JPanel();
        pns_2.setLayout(new BorderLayout());
        pns_2.setBorder(new EmptyBorder(50, 50, 0, 50));
        pns_2.setBackground(Color.white);

        JTextPane lblTitle_2 = new JTextPane();
        StyledDocument doc = lblTitle_2.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);

        try {
            doc.insertString(doc.getLength(), "你人生中最幸福的那一天是怎樣的一天呢? 是什麼樣的記憶使你那天覺得幸福呢?", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        lblTitle_2.setFont(new Font("HY신명조", Font.BOLD, 30));
        lblTitle_2.setBorder(new EmptyBorder(150, 100, 0, 50));

        txt_2 = new JTextArea("< 慢慢地, 在這裡輸入");
        txt_2.setForeground(Color.GRAY);
        txt_2.setFont(new Font("바탕", Font.BOLD, 30));
        txt_2.setLineWrap(true);
        txt_2.setWrapStyleWord(true);
        txt_2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txt_2.getText().equals("< 慢慢地, 在這裡輸入")) {
                    txt_2.setText("");
                    txt_2.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txt_2.getText().isEmpty()) {
                    txt_2.setForeground(Color.GRAY);
                    txt_2.setText("< 慢慢地, 在這裡輸入");
                }
            }
        });

        txt_2.addKeyListener(this);

        pns_2.add(lblTitle_2, BorderLayout.NORTH);
        pns_2.add(txt_2, BorderLayout.CENTER);

        pn_2_center.add(lblTitle_2, BorderLayout.NORTH);
        pn_2_center.add(pns_2, BorderLayout.CENTER);
        pn_2.add(pn_2_right, BorderLayout.EAST);
        pn_2.add(pn_2_left, BorderLayout.WEST);
        pn_2.add(pn_2_top, BorderLayout.NORTH);
        pn_2.add(pn_2_bottom, BorderLayout.SOUTH);
        pn_2.add(pn_2_center, BorderLayout.CENTER);
        pn_2.setOpaque(false);
        alpha = 0f;

        add(pn_2, BorderLayout.CENTER);
        revalidate();
        repaint();

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
        char c = e.getKeyChar();
        text += c; // 사용자가 타이핑한 문자를 현재 텍스트에 추가

        // 트리거 단어 감지 및 처리
        for (String trigger : triggerWords) {
            if (text.contains(trigger)) {
                String randomStr = randomChineseStrings[new Random().nextInt(randomChineseStrings.length)];
                text += " " + randomStr;
                txt_2.setText(text);
                txt_2.setCaretPosition(text.length());
                text = ""; // 텍스트 초기화
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (currentScreen == 1 && e.getKeyCode() == KeyEvent.VK_ENTER) {
            // 첫 번째 화면에서 엔터 키를 눌렀을 때
            // ... 첫 번째 화면에서 두 번째 화면으로 전환하는 코드 ...

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("Enter key");
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
        }}else if(currentScreen == 2){
            text = txt_2.getText();
            for (String a : triggerList){
                System.out.println("이것은 a"+a);
                text = text.replace(a,"");
                System.out.println("리스트 안"+triggerList);
            }
            for (String trigger : triggerWords) {
                if (text.contains(trigger)) {
                    triggerList.add(trigger);
                    String randomStr = randomChineseStrings[new Random().nextInt(randomChineseStrings.length)];
//                    txt_2.setText(txt_2.getText() + " " + randomStr);
                    addTextOneByOne(" " + randomStr, 50);
                    txt_2.setCaretPosition(txt_2.getText().length());
                    System.out.println(text);
                    text = ""; // 텍스트 초기화
                    break;
                }
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_F10) {
            // 초기화해야 할 모든 변수들을 초기 상태로 되돌립니다.
            resetApplication();
        }



    }
    private void resetApplication(){
        text = "";
        triggerList.clear();
        currentScreen = 1;

        // 첫 번째 화면을 다시 로드
        remove(pn_2); // 현재 화면을 제거
        FirstScreen(); // 첫 번째 화면을 다시 로드

        // 필요한 경우 추가적인 초기화 코드
        // ...

        revalidate();
        repaint();
    }
    private void addTextOneByOne(String textToAdd, int delay) {
        Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener() {
            private int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < textToAdd.length()) {
                    txt_2.append(String.valueOf(textToAdd.charAt(index)));
                    index++;
                } else {
                    // 문자열이 완전히 추가되면 타이머 정지
                    timer.stop();
                }
            }
        });
        timer.start();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        // 필요한 경우 여기에 코드를 추가
    }

    // 메인 메소드 또는 다른 필요한 메소드들
    // ...



}
