import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.io.*;
import java.util.Base64;

public class HangedMan extends WindowAdapter implements ActionListener {
    JFrame frame = new JFrame("Hanged Man");
    JLabel pic;
    String progress = "";
    int j = 0;
    String randomWord;
    char[] chars;
    JTextField  input;
//    String[] array = {"Kick", "Stupid", "Dumb"};
//    double rand;
//    int randomNum;
//    int arrLen;
    int stringLen;
    int level = 1;
    int score;
    JLabel scoreLabel;
    JLabel levelLabel;
    String [][] nestedList;
    JLabel trackerLabel;
    String tracker = "Letters entered: ";

    private SecretKey key;
    private final int KEY_SIZE = 128;
    private final int DATA_LENGTH = 128;
    private Cipher encryptionCipher;

    boolean debug = false;
//    boolean debug = true;

    public HangedMan() throws Exception {


        // File path is passed as parameter
        File file = new File("src/nkdaklw");
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));
        // Declaring a string variable
        String st;
        st = br.readLine();

        int stringLenght = st.length();
        String word = "";
        String[] words = new String[95885];
        String[] one = new String[4455];
        String[] two = new String[6278];
        String[] three = new String[10179];
        String[] four = new String[12568];
        String[] five = new String[14445];
        String[] six = new String[14114];
        String[] seven = new String[11956];
        String[] eight = new String[8700];
        String[] nine = new String[5967];
        String[] ten = new String[3611];
        String[] eleven = new String[3612];
        int wordCount = 0;
        int wordLenght;
        int oneCount = 0;
        int twoCount = 0;
        int threeCount = 0;
        int fourCount = 0;
        int fiveCount = 0;
        int sixCount = 0;
        int sevenCount = 0;
        int eightCount = 0;
        int nineCount = 0;
        int tenCount = 0;
        int elevenCount = 0;
        int x;

        for(x = 0; x < stringLenght; x++) {
            char letter = st.charAt(x);
//            System.out.println(letter);
            switch (letter) {
                case ',': {
                    words[wordCount] = word;
                    wordCount++;
                    word = "";
                    break;
                }
                default:
                    word = word + letter;
                    break;
            }
//            if (x > 20) { // debug
//                break;
//            }
//            else {
//                System.out.println(letter);
//            }
        }
//        System.out.println(words[95884]); // debug

        for(x = 0; x < 95885; x++) {
            word = words[x];
            wordLenght = word.length();
            if (wordLenght < 5) {
                one[oneCount] = word;
                oneCount++;
            }
            else if (wordLenght > 13) {
                eleven[elevenCount] = word;
                elevenCount++;
            }
            else {
                switch (wordLenght) {
                    case (5): {
                        two[twoCount] = word;
                        twoCount++;
                        break;
                    }
                    case (6): {
                        three[threeCount] = word;
                        threeCount++;
                        break;
                    }
                    case (7): {
                        four[fourCount] = word;
                        fourCount++;
                        break;
                    }
                    case (8): {
                        five[fiveCount] = word;
                        fiveCount++;
                        break;
                    }
                    case (9): {
                        six[sixCount] = word;
                        sixCount++;
                        break;
                    }
                    case (10): {
                        seven[sevenCount] = word;
                        sevenCount++;
                        break;
                    }
                    case (11): {
                        eight[eightCount] = word;
                        eightCount++;
                        break;
                    }
                    case (12): {
                        nine[nineCount] = word;
                        nineCount++;
                        break;
                    }
                    case (13): {
                        ten[tenCount] = word;
                        tenCount++;
                        break;
                    }
                    default: {
                        break;
                    }
                }
            }
        }
//        System.out.println(one[4454]); // debug
//        System.out.println(two[6277]); // debug
//        System.out.println(three[10178]); // debug
//        System.out.println(four[12568-1]); // debug
//        System.out.println(five[14445-1]); // debug
//        System.out.println(six[14114-1]); // debug
//        System.out.println(seven[11956-1]); // debug
//        System.out.println(eight[8700-1]); // debug
//        System.out.println(nine[5967-1]); // debug
//        System.out.println(ten[3611-1]); // debug
//        System.out.println(eleven[3611]); // debug

//        System.out.println(st); // debug
        String[][] list = new String[][] {one, two, three, four, five, six, seven, nine, eight, nine, ten, eleven};
//        System.out.println(list[0].length); // debug

        nestedList = list;
        frame.setSize(500,400);
        pic = new JLabel();
        pic.setBounds(10, 10, 500, 218);
        initVars();
        score = 0;

        JButton changeLevel = new JButton("Change Level");
        changeLevel.setBounds(360,330,120,30);
        scoreLabel = new JLabel();
        scoreLabel.setBounds(230,0,150,20);
        levelLabel = new JLabel();
        levelLabel.setBounds(432,0,150,20);
        trackerLabel = new JLabel();
        trackerLabel.setBounds(80,340,200,20);
        frame.add(trackerLabel);
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
                String inputLevel = JOptionPane.showInputDialog(null, "Enter a level of between 1 and 11.");
                int levelInput = 0;
                try {
                    levelInput = Integer.parseInt(inputLevel);
                } catch (NumberFormatException i) {
                    levelInput = level;
                }
                level = levelInput;
                if (level > 11) {
                    level = 11;
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
        int i, arrLen, randomNum, x, hints, h;
//        System.out.println(nestedList.length + " " + level); // debug
        double rand = Math.random();
        i = level - 1;
        String[] array = nestedList[i];
        arrLen = array.length; //
        randomNum = ((int) (rand * 1000)) % arrLen;
        randomWord = array[randomNum];
        stringLen = randomWord.length();
        chars = new char[stringLen];
        Arrays.fill(chars, '-');
        progress = new String(chars);
        tracker = "Letters entered: ";

        // give some random hints as letters with their pos in the word
        if (stringLen < 8) {
            hints = 1;
        }
        else {
            hints = 2;
        }
        int[] prevHints = new int[hints];
        for (x = 0; x < hints; x++) {
            while (true) {
                rand = Math.random();
                randomNum = (((int) (rand * 100)) % stringLen);
                int flag = 1;

                for (h = 0; h < hints; h++) {
                    if (prevHints[h] == randomNum) {
                        flag = 0;
                        break;
                    }
                }

                if (flag == 1) {
                    prevHints[x] = randomNum;
                    randomNum = (((int) (rand * 100)) % stringLen);
                    char randomChar = randomWord.charAt(randomNum);
                    progress = progress.substring(0, randomNum) + randomChar + progress.substring(randomNum + 1);
                    break;
                }
            }
        }

        if (debug ==  true) {
            JOptionPane.showMessageDialog(frame, "Word: " + randomWord);
        }
    }

    public void actionPerformed(ActionEvent e) {
        input.requestFocus();
        String text = input.getText();
        input.setText("");

        if (text.length() == 0) {
            text = " ";
        }

        int i, x, check;
        char charForComparison, ch;
        int[] positions;
        for (i = 0; i < text.length(); i++) {
            ch = text.charAt(i);
            tracker = tracker + ch + " ";

            check = 0;
            positions = new int[20];

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

            if (check == 0) {
                j++;
                displayImage(j);
                if (j == 7) {
                    randomWord = randomWord.substring(0, 1).toUpperCase() + randomWord.substring(1);
                    String messege = "The word was '" + randomWord + "' but You let him DIE. Hope you enjoyed it!!!";
                    JOptionPane.showMessageDialog(frame, messege);
                    System.exit(1);
                }
            }
            else {
                displayImage(j);
            }

            String randomWordLower = randomWord.toLowerCase();
            if (randomWordLower.equals(progress.toLowerCase())) {
                score += (stringLen * 10) + ((stringLen * stringLen) * 2);
                initVars();
                displayImage(j);
            }
        }
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
        trackerLabel.setText(tracker);
        frame.add(pic);
    }

    public static void main(String[] args) throws Exception {
        HangedMan obj = new HangedMan();
    }
}

