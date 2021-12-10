package com.github.wahidabd.fp;

import com.github.wahidabd.fp.start.Level;
import com.github.wahidabd.fp.start.Start;
import com.github.wahidabd.fp.start.Welcome;
import com.github.wahidabd.fp.utils.Constant;

import java.awt.*;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		Welcome welcome = new Welcome(5);
		welcome.showAndWelcome();

//		JFrame obj = Constant.frame;
		Start start = new Start();

		Constant.frame(start);

//		obj.setBounds(10,10,905,700);
//		obj.setBackground(Color.DARK_GRAY);
//		obj.setResizable(false);
//		obj.setVisible(true);
//		obj.setLocationRelativeTo(null);
//		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		obj.add(start);
	}

}
