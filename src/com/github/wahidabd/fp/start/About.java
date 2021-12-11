package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;

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
    }

    public void paint(Graphics g){
        ImageIcon about = new ImageIcon(Constant.IMAGE_ABOUT_US);
        about.paintIcon(this, g, 0, 0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_X){
            Functions.dispose();
            System.out.println("Back");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
