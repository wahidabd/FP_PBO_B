package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.gameplay.Gameplay;
import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;
import com.github.wahidabd.fp.utils.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Level extends JPanel {
//	private ImageIcon chooseLevel;
    private ImageIcon back;
    private ImageIcon easy;
    private ImageIcon medium;
    private ImageIcon hard;
    
    private Sound soundMainMenu = new Sound();
    private Sound soundClick = new Sound();
   
    public Level(){
        setFocusable(true);
        setOpaque(false);
        
        soundMainMenu.setFile(Constant.MUSIC_MAINMENU);
        soundMainMenu.playMusic(0.3);
        soundClick.setFile(Constant.MUSIC_CLICK);
        
        showPanel();
    }

    private void showPanel(){
//        chooseLevel = new ImageIcon(Constant.CHOOSE_LEVEL);
        back = new ImageIcon(Constant.BACK_IMAGE);
        easy = new ImageIcon(Constant.EASY_LEVEL);
        medium = new ImageIcon(Constant.MEDIUM_LEVEL);
        hard = new ImageIcon(Constant.HARD_LEVEL);
        
        // when mouse click
        mouseListener();
    }

    private void action(int delay, int level){

        Gameplay gamePlay = new Gameplay(delay, level);
        Functions.dispose();
        Functions.frame(gamePlay);
        repaint();
    }

    private void mouseListener(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if ((x >= 338 && x <= 567) && (y >= 271 && y <= 327))
                {
                    soundClick.play();
                    action(75, 1);
                    soundMainMenu.stop();
                }
                else if ((x >= 338 && x <= 567) && (y >= 347 && y <= 404))
                {
                    soundClick.play();
                    action(60, 2);
                    soundMainMenu.stop();
                }
                else if ((x >= 338 && x <= 567) && (y >= 323 && y <= 479))
                {
                    soundClick.play();
                    action(45, 3);
                    soundMainMenu.stop();
                }
                else if ((x >= 52 && x <= 79) && (y >= 40 && y <= 61))
                {
                    soundClick.play();
                    soundMainMenu.stop();
                    Start start = new Start();
                    Functions.dispose();
                    Functions.frame(start);
                }

                // get coordinate
                System.out.println("X= " + x + ", Y=" + y);
            }
        });
    }

    public void paint(Graphics g){

        // background
        ImageIcon background = new ImageIcon(Constant.BACKGROUND_IMAGE);
        background.paintIcon(this, g, 0, 0);

        // position all image
        back.paintIcon(this, g, 49, 36);
        easy.paintIcon(this, g, 338, 271);
        medium.paintIcon(this, g, 338, 347);
        hard.paintIcon(this, g, 338, 423);


    }

}