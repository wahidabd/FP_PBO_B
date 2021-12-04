//Part1 Start
import java.awt.*;

import javax.swing.*;

public class Main extends JWindow {

	private int duration;
	private JProgressBar progressBar;
	private Welcome welcome;
	private JPanel panel;

	public Main(int d){
		duration = d;
		setSize(500, 300);
		setLocationRelativeTo(null);
	}

	public void showWelcome(){
		welcome = new Welcome();

		panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 40));
		panel.setOpaque(false);

		progressBar = new JProgressBar(0, 100);
		progressBar.setStringPainted(false);
		progressBar.setPreferredSize(new Dimension(getWidth() -10, 15));
		progressBar.setForeground(Color.GREEN);
		progressBar.setVisible(true);
		panel.add(progressBar);

		welcome.add(progressBar, "Top");
		getContentPane().add(welcome, "Center");

		setVisible(true);

		for (int i = 0; i < 100; i++) {
			try {
				progressBar.setValue(i);
				Thread.sleep(duration);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		setVisible(false);
	}

	public void showAndWelcome(){
		showWelcome();
		dispose();
	}

	public static void main(String[] args) {
		// Welcome Scree
		Main welcome = new Main(30);
		welcome.showAndWelcome();

		// Game Play
		JFrame obj = new JFrame();
		GamePlay gameplay = new GamePlay();

		obj.setBounds(10,10,905,700);
		obj.setBackground(Color.DARK_GRAY);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(gameplay);
		//Part 1 End
	}

}
