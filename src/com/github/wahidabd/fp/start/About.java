package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class About extends JPanel implements KeyListener {

//    private final JFrame frame;

    public About(){
        setSize(Constant.WIDTH, Constant.HEIGHT);
        addKeyListener(this);
        setFocusable(true);
//        frame = new JFrame();
//        frame.getContentPane();
//        frame.setLocationRelativeTo(null);
    }

    public void paint(Graphics g){
//        ImageIcon background = new ImageIcon(Constant.BACKGROUND_IMAGE);
//        background.paintIcon(this, g, 0, 0);

        ImageIcon about = new ImageIcon(Constant.IMAGE_ABOUT_US);
        about.paintIcon(this, g, 0, 0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Q){
//            Constant.dispose();
            System.out.println("Back");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
