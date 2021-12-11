package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.gameplay.GamePlay;

import javax.swing.*;
import java.awt.*;

public class Level extends JPanel {

    private final JFrame frame;

    private JButton buttonEasy;
    private JButton buttonNormal;
    private JButton buttonHard;

    public Level(){
        // Set center in screen and set size screen
        frame = new JFrame();
        setSize(905, 700);
        frame.setLocationRelativeTo(null);
        frame.getContentPane();
        this.setLayout(null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    }

    private void buttonHandle(){

        int yEasy = 50;
        int yNormal = yEasy * 2;
        int yHard = yNormal + 50;

        // Set button Easy
        buttonEasy = new JButton();
        buttonEasy.setText("Easy");
        buttonEasy.setFocusable(false);
        buttonEasy.setVisible(true);
        buttonEasy.setBounds(700/2, yEasy, 200, 40);
        buttonEasy.addActionListener(click -> action(70, 1));


        buttonNormal = new JButton();
        buttonNormal.setText("Normal");
        buttonNormal.setFocusable(false);
        buttonNormal.setVisible(true);
        buttonNormal.setBounds(700/2, yNormal, 200, 40);
        buttonNormal.addActionListener(click -> action(50, 2));

        buttonHard = new JButton();
        buttonHard.setText("Hard");
        buttonHard.setFocusable(false);
        buttonHard.setVisible(true);
        buttonHard.setBounds(700/2, yHard, 200, 40);
        buttonHard.addActionListener(click -> action(20, 3));
    }

    private void action(int delay, int level){

        GamePlay gamePlay = new GamePlay(delay, level);
        frame.setBounds(10,10,905,700);
        frame.setBackground(Color.DARK_GRAY);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePlay);

        repaint();
    }

    public void paint(Graphics g){

        buttonHandle();
        this.add(buttonEasy);
        this.add(buttonNormal);
        this.add(buttonHard);

        Dimension dimension = this.getSize();

        g.setColor(Color.CYAN);
        g.setFont(new Font("areal", Font.BOLD, 24));
        String select = "Select Level";
        FontMetrics fm = g.getFontMetrics();
        int x = (dimension.width - fm.stringWidth(select)) / 2;
        g.drawString(select,  x, 35);

        g.dispose();
    }

}
