package com.github.wahidabd.fp.start;

import javax.swing.*;
import java.awt.*;

public class ImageSplash extends JPanel {

    private final ImageIcon image;

    public ImageSplash(){
        image = new ImageIcon("assets/loading_Screen.png");
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        image.paintIcon(this, g, 0, 0);

    }
}