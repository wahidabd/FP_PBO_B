package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.Main;
import com.github.wahidabd.fp.gameplay.GamePlay;
import com.github.wahidabd.fp.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Start extends JPanel implements KeyListener {

    private final JFrame frame;
    private JButton button;

    private ImageIcon startImage;
    private ImageIcon aboutImage;

    public Start(){
        setSize(905, 700);
        setFocusable(true);
        addKeyListener(this);

        frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.getContentPane();
        this.setLayout(null);
        setOpaque(false);

        showPanel();
    }

    private void showPanel(){
        startImage = new ImageIcon(Constant.START_IMAGE);
        aboutImage = new ImageIcon(Constant.ABOUT_IMAGE);
    }

    private void toAbout(){
        About about = new About();

        Constant.frame(about);
//        JFrame obj = new JFrame();
        repaint();
    }

    private void toLevel(){
        Level level = new Level();
        Constant.frame(level);
        repaint();
    }

    public void paint(Graphics g){

        // background
        ImageIcon background = new ImageIcon(Constant.BACKGROUND_IMAGE);
        background.paintIcon(this, g, 0, 0);

        // image icon
        startImage.paintIcon(this, g, Constant.HEIGHT / 2 - 12, 250);
        aboutImage.paintIcon(this, g, Constant.HEIGHT / 2 - 12, 350);

        Dimension dimension = this.getSize();

        g.setColor(Color.WHITE);
        g.setFont(new Font("poppins", Font.BOLD, 20));
        String select = "1. Start  2. About Us";
        FontMetrics fm = g.getFontMetrics();
        int x = (dimension.width - fm.stringWidth(select)) / 2;
        g.drawString(select,  x, 620);
    }


    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_1:
                System.out.println("START");
                toLevel();
                break;

            case KeyEvent.VK_2:
                System.out.println("ABOUT");
                toAbout();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
