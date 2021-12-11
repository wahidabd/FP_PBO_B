package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class About extends JPanel {

    private final ImageIcon back;

    public About(){
        setSize(Constant.WIDTH, Constant.HEIGHT);
        setFocusable(true);

        back = new ImageIcon(Constant.BACK_IMAGE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if ((x >= 52 && x <= 79) && (y >= 40 && y <= 61)){
                    Start start = new Start();

                    Functions.dispose();
                    Functions.frame(start);
                }

            }
        });
    }

    public void paint(Graphics g){
        ImageIcon about = new ImageIcon(Constant.IMAGE_ABOUT_US);
        about.paintIcon(this, g, 0, 0);

        back.paintIcon(this, g, 49, 36);

    }
}
