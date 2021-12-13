package com.github.wahidabd.fp.start;

import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;
import com.github.wahidabd.fp.utils.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HighScore extends JPanel {

    private final ImageIcon back;
    private Sound soundMainMenu = new Sound();
    private Sound soundClick = new Sound();

    public HighScore(){
        setSize(Constant.WIDTH, Constant.HEIGHT);
        setFocusable(true);
        
        soundMainMenu.setFile(Constant.MUSIC_MAINMENU);
        soundMainMenu.playMusic(0.3);
        soundClick.setFile(Constant.MUSIC_CLICK);

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
                    soundClick.play();
                    soundMainMenu.stop();
                }

            }
        });
    }

    public void paint(Graphics g){
        ImageIcon highScore = new ImageIcon(Constant.SCORE_IMAGE);
        highScore.paintIcon(this, g, 0, 0);

        back.paintIcon(this, g, 49, 36);

    }
}
