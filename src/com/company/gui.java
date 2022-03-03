import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class HangedMan extends WindowAdapter implements ActionListener {
    JFrame frame = new JFrame("Hanged Man");
    JLabel pic;
    String progress = "";
    int j = 0;
    String randomWord;
    char[] chars;
    JTextField  input;
    String[] array = {"Kick", "Stupid", "Dumb"};
    double rand;
    int randomNum;
    int arrLen;
    int stringLen;
    int level;
    int score;
    JLabel scoreLabel;
    JLabel levelLabel;

    public HangedMan() {
        frame.setSize(500,400);
        pic = new JLabel();
        pic.setBounds(10, 10, 500, 218);
        initVars();
        level = 1;
        score = 0;

        JButton changeLevel = new JButton("Change Level");
        changeLevel.setBounds(360,330,120,30);
        scoreLabel = new JLabel();
        scoreLabel.setBounds(230,0,150,20);
        levelLabel = new JLabel();
        levelLabel.setBounds(432,0,150,20);
        frame.add(levelLabel);
        frame.add(scoreLabel);
        frame.add(changeLevel);
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(5,330,60,30);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a=JOptionPane.showConfirmDialog(frame,"Do you want to Quit");
                if (a == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Your Final Score Was " + score);
                    System.exit(0);
                }
            }
        });
        frame.add(quitButton);

        input = new JTextField();
        JButton button =new JButton("Continue");
        JLabel output = new JLabel("Enter a character: ");
        output.setBounds(120,270,150,30);
        input.setBounds(235,270,30,30);
        button.setBounds(203,300,95,30);
        frame.add(button);
        frame.add(input);
        frame.add(output);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(true);

        changeLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputLevel = JOptionPane.showInputDialog(null, "Enter a level of between 1 and 8.");
                int levelInput = 0;
                try {
                    levelInput = Integer.parseInt(inputLevel);
                } catch (NumberFormatException i) {
                    levelInput = level;
                }
                level = levelInput;
                if (level > 8) {
                    level = 8;
                }
                else if (level < 1) {
                    level = 1;
                }
                displayImage(j);

//                System.out.println(levelInput); // debug


            }
        });

        displayImage(j);
        JOptionPane.showMessageDialog(frame, "Welcome to Hanged Man! Hope you have FUN.");
        button.addActionListener(this);
    }

    public void initVars() {
        j = 0;
        rand = Math.random();
        arrLen = array.length;
        randomNum = ((int) (rand * 1000)) % arrLen;
        randomWord = array[randomNum];
        stringLen = randomWord.length();
        chars = new char[stringLen];
        Arrays.fill(chars, '-');
        progress = new String(chars);
    }

    public void actionPerformed(ActionEvent e) {

        if (j == 6) {
            displayImage(7);
            JOptionPane.showMessageDialog(frame, "You let him DIE. Hope you enjoyed it!!!");
            System.exit(0);
        }
        input.requestFocus();
        String text = input.getText();
        input.setText("");

        if (text.length() == 0) {
            text = " ";
        }

        char ch = text.charAt(0);
        int stringLen = randomWord.length();
        char charForComparison;

        int check = 0;
        int[] positions = new int[20];
        int x;

        for(x = 0; x < stringLen; x++) {
            charForComparison = randomWord.charAt(x);
            charForComparison = Character.toLowerCase(charForComparison);

            if (charForComparison == ch) {
                check = 1;
                positions[x] = 1;
//                JOptionPane.showMessageDialog(frame, ch); // debug
            }
        }

        for(x = 0; x < 20; x++) {
            if (positions[x] == 1) {
                progress = progress.substring(0, x) + ch + progress.substring(x + 1);
            }
        }

        String randomWordLower = randomWord.toLowerCase();

        if (randomWordLower.equals(progress.toLowerCase())) {
            score = (level * 10 )+ ((level * level * level) * 2);
            initVars();
            displayImage(j);
        }

        if (check == 0) {
            j++;
        }

        displayImage(j);
    }

    public void displayImage(int j) {
        String imgPath = "src/img/img" + j + ".png";
        ImageIcon img = new ImageIcon(imgPath);
        pic.setIcon(img);
//        System.out.println(progress); // debug
        progress = progress.substring(0, 1).toUpperCase() + progress.substring(1);
        pic.setText("Your Progress: " + progress);

        scoreLabel.setText("Score: " + score);
        levelLabel.setText("Level: " + level);
        frame.add(pic);
    }

    public static void main(String[] args) {
        HangedMan obj = new HangedMan();
    }
}
