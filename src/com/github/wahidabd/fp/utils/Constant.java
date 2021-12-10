package com.github.wahidabd.fp.utils;

import javax.swing.*;
import java.awt.*;

public class Constant {

    public static final int WIDTH = 905;
    public static final int HEIGHT = 700;

    // Image
    public static final String BACKGROUND_IMAGE = "assets/background.png";
    public static final String IMAGE_ABOUT_US = "assets/aboutus.png";
    public static final String START_IMAGE = "assets/start.png";
    public static final String ABOUT_IMAGE = "assets/about.png";

    // Snake Color
    public static final String LEFT_GREEN = "assets/headLeftGreen.png";
    public static final String UP_GREEN = "assets/headUpGreen.png";
    public static final String RIGHT_GREEN = "assets/headRightGreen.png";
    public static final String DOWN_GREEN = "assets/headDownGreen.png";

    public static final String LEFT_BLUE = "assets/headLeftBlue.png";
    public static final String UP_BLUE = "assets/headUpBlue.png";
    public static final String RIGHT_BLUE = "assets/headRightBlue.png";
    public static final String DOWN_BLUE = "assets/headDownBlue.png";

    public static final String LEFT_RED = "assets/headLeftRed.png";
    public static final String UP_RED = "assets/headUpRed.png";
    public static final String RIGHT_RED = "assets/headRightRed.png";
    public static final String DOWN_RED = "assets/headDownRed.png";

    public static void frame(JPanel panel){
        JFrame obj = new JFrame();

        obj.setBounds(10,10,905,700);
        obj.setBackground(Color.decode("#6BBDB9"));
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setLocationRelativeTo(null);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(panel);
    }

}
