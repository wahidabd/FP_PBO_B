package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.gameplay.GamePlay;
import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;
import com.github.wahidabd.fp.utils.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level extends JPanel {
	private ImageIcon chooseLevel;
    private ImageIcon back;
    private ImageIcon easy;
    private ImageIcon medium;
    private ImageIcon hard;
   
    public Level(){
        setFocusable(true);
        setOpaque(false);

        showPanel();
    }

    private void showPanel(){
        chooseLevel = new ImageIcon(Constant.CHOOSE_LEVEL);
        back = new ImageIcon(Constant.BACK_IMAGE);
        easy = new ImageIcon(Constant.EASY_LEVEL);
        medium = new ImageIcon(Constant.MEDIUM_LEVEL);
        hard = new ImageIcon(Constant.HARD_LEVEL);
        
        // when mouse click
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if ((x >= 338 && x <= 567) && (y >= 271 && y <= 327)){
                    action(90, 1);
                }else if ((x >= 338 && x <= 567) && (y >= 347 && y <= 404)){
                    action(60, 2);
                }else if ((x >= 338 && x <= 567) && (y >= 323 && y <= 479)){
                    action(30, 3);
                }else if ((x >= 52 && x <= 79) && (y >= 40 && y <= 61)){
                    Start start = new Start();
                    Functions.dispose();
                    Functions.frame(start);
                }

                // get coordinate
                System.out.println("X= " + x + ", Y=" + y);
            }
        });
    }

    private void action(int delay, int level){

        GamePlay gamePlay = new GamePlay(delay, level);
        Functions.dispose();
        Functions.frame(gamePlay);
        repaint();
    }

    public void paint(Graphics g){

        // background
        ImageIcon background = new ImageIcon(Constant.BACKGROUND_IMAGE);
        background.paintIcon(this, g, 0, 0);

        // position all image
        chooseLevel.paintIcon(this, g, 254, 32);
        back.paintIcon(this, g, 49, 36);
        easy.paintIcon(this, g, 338, 271);
        medium.paintIcon(this, g, 338, 347);
        hard.paintIcon(this, g, 338, 423);


    }

}
