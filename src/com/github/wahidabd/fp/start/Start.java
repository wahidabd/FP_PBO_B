package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;
import com.github.wahidabd.fp.utils.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start extends JPanel {

    private JButton button;

    private ImageIcon startImage;
    private ImageIcon aboutImage;
    private ImageIcon scoreImage;
    
    private Sound soundMainMenu = new Sound();
    private Sound soundClick = new Sound();

    public Start(){
        setFocusable(true);
        setOpaque(false);
        
        showPanel();
        soundMainMenu.setFile(Constant.MUSIC_MAINMENU);
   		soundMainMenu.playMusic(0.3);
   		soundClick.setFile(Constant.MUSIC_CLICK);
           
    }

    private void showPanel(){
        startImage = new ImageIcon(Constant.START_IMAGE);
        aboutImage = new ImageIcon(Constant.ABOUT_IMAGE);
        scoreImage = new ImageIcon(Constant.SCORE_IMAGE);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if ((x >= 342 && x <= 585) && (y >= 252 && y <= 325)){
                    System.out.println("LEVEL");
                    soundClick.play();;
                    
                    toLevel();
                    soundMainMenu.stop();
                }else if((x >= 342 && x <= 585) && (y >= 352 && y <= 400)){
                    System.out.println("ABOUT");
                    toAbout();

                    soundClick.play();;
                    soundMainMenu.stop();
                }else if ((x >= 313 && x <= 591) && (y >= 427 && y <= 483)){
                    toHighScore();

                    soundClick.play();
                    soundMainMenu.stop();
                }

                // get coordinate
                System.out.println("X= " + x + ", Y=" + y);
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

    private void toHighScore(){
        HighScore highScore = new HighScore();
        Functions.dispose();
        Functions.frame(highScore);
        repaint();
    }

    public void paint(Graphics g){

        // background
        ImageIcon background = new ImageIcon(Constant.BACKGROUND_MAINMENU);
        background.paintIcon(this, g, 0, 0);

        // image icon
        startImage.paintIcon(this, g, 338, 269);
        aboutImage.paintIcon(this, g, 338, 348);
        scoreImage.paintIcon(this, g, 338, 786);
    }
}