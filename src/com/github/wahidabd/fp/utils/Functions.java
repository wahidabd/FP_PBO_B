package com.github.wahidabd.fp.utils;

import javax.swing.*;
import java.awt.*;

public class Functions {

    private static JFrame frame;

    public static void frame(JPanel panel){
        frame = new JFrame();

        frame.setBounds(10,10,905,700);
        frame.setBackground(Color.decode("#6BBDB9"));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    }

    public static void dispose(){
        frame.setVisible(false);
        frame.dispose();
    }
}
