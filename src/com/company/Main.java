package com.company;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.Math;
import java.lang.String;
import java.util.Scanner;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

// json-simple-1.1.jar
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main {
    public static void displayImage(String file) {
        try
        {
            JFrame f = new JFrame("Add an Image to a JPanel");
            JPanel panel = new JPanel();
            panel.setBounds(50, 50, 250, 250);
            BufferedImage img = ImageIO.read(new File(file));
            JLabel pic = new JLabel(new ImageIcon(img));
            panel.add(pic);
            f.add(panel);
            f.setSize(400, 400);
            f.setLayout(null);
            f.setVisible(true);
        }
        catch (IOException e) {}
    }

    public static void main(String[] args) {
//        String[8] files = ["img/1.png", ];
    }

        public static void game(String[] args) {
	// write your code here
        double rand;
        int randomNum;
        int arrLen;
        String randomWord;
        int x;
        int j = 0;
        char ch;
        char cForComparison;
        int i;
        int pos;
        int finish = 0;
        int stringLen;
        int check;
        String hi;
        Scanner scan = new Scanner(System.in);

        String[] array = {"Kick", "Stupid", "Dumb"};




        String[] hangedMan = {
                "___________\n|   \n|   \n|  \n|   \n|  \n|___________",
                "___________\n|   |\n|   \n|  \n|   \n|  \n|___________",
                "___________\n|   |\n|   O\n|  \n|   \n|  \n|___________",
                "___________\n|   |\n|   O\n|  /\n|   \n|  \n|___________",
                "___________\n|   |\n|   O\n|  /|\n|   \n|  \n|___________",
                "___________\n|   |\n|   O\n|  /|\\\n|   \n|  \n|___________",
                "___________\n|   |\n|   O\n|  /|\\\n|   |\n|  \n|___________",
                "___________\n|   |\n|   O\n|  /|\\\n|   |\n|  / \n|___________",
                "___________\n|   |\n|   O\n|  /|\\\n|   |\n|  / \\\n|___________"
        };

        rand = Math.random();
        arrLen = array.length;
        randomNum = ((int) (rand * 100) % arrLen);
        randomWord = array[randomNum];
        stringLen = randomWord.length();

        char[] chars = new char[stringLen];
        Arrays.fill(chars, '-');
        String word = new String(chars);

        int[] positions;
        i = 0;

        while(j < 8) {
            check = 0;
            positions = new int[20];
            System.out.println("Your Progress: " + word);
            System.out.print("Guess a character: ");
            ch = scan.next().charAt(0);
//            System.out.println(ch);

            for(x = 0; x < stringLen; x++) {
                cForComparison = randomWord.charAt(x);
                cForComparison = Character.toLowerCase(cForComparison);
//                System.out.println(cForComparison + ": " + ch);

                if (cForComparison == ch) {
                    positions[x] = 1;
                    check = 1;
//                    System.out.print("-");
                }
            }

            for(x = 0; x < 20; x++) {
                pos = positions[x];
                if (pos > 0)
                {
//                    System.out.println(pos);
                    word = word.substring(0, x) + ch
                            + word.substring(x + 1);
                }
            }

            hi = word.substring(0, 1).toUpperCase() + word.substring(1);

            if (hi.equals(randomWord)) {
                System.out.println("You Won!!!");
                finish = 0;
                break;
            }

            if (check != 1) {
                j++;
            }
            System.out.println(hangedMan[j]);

            System.out.println("");
        }

        if (j == 8) System.out.println("He is Dead Now!!! :) Ez");
        else System.out.println("Well well, guess some managed to survive... for now!");
    }
}
