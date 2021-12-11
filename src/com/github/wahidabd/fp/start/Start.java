package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start extends JPanel {

//    private final JFrame frame;
    private JButton button;

    private ImageIcon startImage;
    private ImageIcon aboutImage;

    public Start(){
        setFocusable(true);
        setOpaque(false);

        showPanel();
    }

    private void showPanel(){
        startImage = new ImageIcon(Constant.START_IMAGE);
        aboutImage = new ImageIcon(Constant.ABOUT_IMAGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if ((x >= 342 && x <= 585) && (y >= 252 && y <= 300)){
                    System.out.println("LEVEL");
                    toLevel();
                }else if((x >= 342 && x <= 585) && (y >= 352 && y <= 400)){
                    System.out.println("ABOUT");
                    toAbout();
                }

            }
        });
    }

    private void toAbout(){
        About about = new About();
        Functions.dispose();
        Functions.frame(about);
        repaint();
    }

    private void toLevel(){
        Level level = new Level();
        Functions.dispose();
        Functions.frame(level);
        repaint();
    }

    public void paint(Graphics g){

        // background
        ImageIcon background = new ImageIcon(Constant.BACKGROUND_IMAGE);
        background.paintIcon(this, g, 0, 0);

        // image icon
        startImage.paintIcon(this, g, 338, 250);
        aboutImage.paintIcon(this, g, 338, 350);

    }

}
