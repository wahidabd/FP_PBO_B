import java.awt.*;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		// Welcome Screen
		Welcome welcome = new Welcome(30);
		welcome.showAndWelcome();

		// Create Frame
		JFrame obj = new JFrame();
		
		// Gameplay
		GamePlay gameplay = new GamePlay();
		
		obj.setBounds(10,10,905,700);
		obj.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setLocationRelativeTo(null);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
	}

}
