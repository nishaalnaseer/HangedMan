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

    public HangedMan() {
        frame.setSize(500,400);
        pic = new JLabel();
        initVars();

        JButton button =new JButton("Click Here");
        input = new JTextField();
        JLabel output = new JLabel("Enter a character: ");
        output.setBounds(120,270,150,30);
        input.setBounds(235,270,30,30);
        button.setBounds(203,300,95,30);
        frame.add(button);
        frame.add(input);
        frame.add(output);
        frame.setLayout(null);
        frame.setVisible(true);

        displayImage(j);
        JOptionPane.showMessageDialog(frame, "Welcome to Hanged Man! Hope you have FUN.");
        button.addActionListener(this);
    }

    public void initVars() {
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
            JOptionPane.showMessageDialog(frame, "Looks like you have managed to survive this Round.");
            int a=JOptionPane.showConfirmDialog(frame,"Do you want to play another Round???");
            if(a==JOptionPane.NO_OPTION){
                System.exit(0);
            }
            else if (a == JOptionPane.YES_OPTION) {
                initVars();
            }
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
        System.out.println(progress);
        progress = progress.substring(0, 1).toUpperCase() + progress.substring(1);
        pic.setText("Your Progress: " + progress);
        pic.setBounds(10, 10, 500, 218);
        frame.add(pic);
    }

    public static void main(String[] args) {
        HangedMan obj = new HangedMan();
    }
}
