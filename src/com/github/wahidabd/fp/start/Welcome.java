package com.github.wahidabd.fp.start;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JWindow {

    private final int duration;

    public Welcome(int d){
        duration = d;
        setSize(905, 700);
        setLocationRelativeTo(null);
    }

    public void showWelcome(){
        ImageSplash imageSplash = new ImageSplash();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 40));
        panel.setOpaque(false);

        // Progress bar top
        JProgressBar progressBar = new JProgressBar(0, 500);
        progressBar.setStringPainted(false);
        progressBar.setPreferredSize(new Dimension(getWidth() -10, 15));
        progressBar.setForeground(Color.GREEN);
        progressBar.setVisible(true);
        panel.add(progressBar);

        imageSplash.add(progressBar, "Top");
        getContentPane().add(imageSplash, "Center");

        setVisible(true);

        for (int i = 0; i < 500; i++) {
            try {
                progressBar.setValue(i);
                Thread.sleep(duration);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        setVisible(false);
    }

    // Show and Exit welcome Screen
    public void showAndWelcome(){
        showWelcome();
        dispose();
    }
}