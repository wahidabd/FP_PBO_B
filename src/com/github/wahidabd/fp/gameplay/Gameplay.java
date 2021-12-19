package com.github.wahidabd.fp.gameplay;

import com.github.wahidabd.fp.start.Level;
import com.github.wahidabd.fp.utils.Constant;
import com.github.wahidabd.fp.utils.Functions;
import com.github.wahidabd.fp.utils.Sound;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class Gameplay extends JPanel implements KeyListener, ActionListener {

	// Create an array to store snake length with maximum length is 750
	private final int[] snakeXLength = new int[750];
	private final int[] snakeYLength = new int[750];

	// Create an variable to store level 
	private final int level;

	// Create variables to control the snake with default values is false
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;

	// Create ImageIcon variables to store all snake head directions
	private ImageIcon headRight;
	private ImageIcon headLeft;
	private ImageIcon headUp;
	private ImageIcon headDown;

	// Create ImageIcon variable to store tail
	private ImageIcon tail;

	// Create variable to initialize the length of snake
	private int lengthofSnake = 3;

	// Create timer to control snake speed
	private final Timer timer;

	// Create variables to store scoreLevel, moves value, score value, and high score value
	private int moves = 0;
	private int score = 0;
	private String highScore = "";
	private int scoreLevel;

	// Create array to store all possible positions that fruit will appear 
	private int [] fruitXpos = {75,100,125,150,175,200,225,250,600,
			625,650,675,700,725,750,775};

	private int [] fruitYpos = {125,150,175,200,225,250,275,300,325,350,
			375,400,425,450,475,525,550};

	// Generate a random fruit position that comes from all the possibilities
	private final Random random = new Random();
	private int xpos = random.nextInt(16); 
	private int ypos = random.nextInt(17); 

	// Sound Effects
	private Sound soundEat = new Sound();
	private Sound soundGameplay = new Sound();
	private Sound soundGameOver = new Sound();

	public Gameplay(int delay, int level) {
		// Add the components that being listened to key events
		addKeyListener(this);
		// Enabling view's focus event on keypad mode 
		setFocusable(true);

		// set level
		this.level = level;

		// Create timer object and start it
		timer = new Timer(delay, this);
		timer.start();

		// Play sound for gameplay
		soundGameplay.setFile(Constant.MUSIC_GAMEPLAY);
		soundGameplay.playMusic(0.3);
	}


	public void paint(Graphics g) {
		// Default snake position
		if (moves == 0) {
			snakeXLength[2] = 150;
			snakeXLength[1] = 175;
			snakeXLength[0] = 200;

			snakeYLength[2] = 175;
			snakeYLength[1] = 175;
			snakeYLength[0] = 175;

		}

		if (highScore.equals(""))
		{
			// Initialize the highscore
			highScore = this.getHighScore();
		}

		// Draw title image
		ImageIcon titleImage = new ImageIcon(Constant.TITLE_IMAGE);
		titleImage.paintIcon(this, g, 25, 5);

		// Draw border for gameplay
		g.setColor(Color.DARK_GRAY);
		g.drawRect(24, 74, 851, 575);

		// Set Background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25,75,850,575);

		// Draw Score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Scores: " + score, 780, 30);

		// Draw snake length
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Length: " + lengthofSnake, 780, 45);

		// Draw walls for medium level
		if (level == 2)
			mediumLevelWall(g);

		// Draw walls for hard level
		if (level == 3)
			hardLevelWall(g);

		// Change snake color according to level that player choose
		snakeColor(level);

		// Add default snake position with head facing right
		headRight.paintIcon(this,g , snakeXLength[0], snakeYLength[0]);

		// Paint different image if different button is pressed
		for(int i = 0; i< lengthofSnake; i++) {
			if(i == 0 && right) 
				headRight.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i == 0 && left) 
				headLeft.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i == 0 && down) 
				headDown.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i == 0 && up) 
				headUp.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i != 0) 
				tail.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

		}

		// Import fruit image
		ImageIcon fruitimage = new ImageIcon(Constant.FRUIT_IMAGE);

		// If fruit is eaten by snake then snake will be bigger and fruit will appear somewhere else 
		if(fruitXpos[xpos] == snakeXLength[0] && fruitYpos[ypos] == snakeYLength[0])
		{
			// play sound if fruit is eaten
			soundEat.setFile(Constant.MUSIC_EAT);
			soundEat.play();

			// Add score depend on the level
			score = score + scoreLevel;

			// Increase snake length by one
			lengthofSnake++;

			// Fruit will appear again in random position
			xpos = random.nextInt(16);
			ypos = random.nextInt(17);
		}

		// Paint the fruit image
		fruitimage.paintIcon(this,g,fruitXpos[xpos], fruitYpos[ypos]);

		for(int i = 1; i<lengthofSnake; i++)
		{
			// If snake head hit his own tail
			if(snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0])
			{
				gameOver(g);
			}

			// Game over condition for medium level
			else if (level == 2)
			{
				// If snake head hit right border 
				if (snakeXLength[i] > 800)
					gameOver(g);

				// If snake head hit left border
				else if (snakeXLength[i] < 75)
					gameOver(g);

				// If snake head hit top border
				else if (snakeYLength[i] < 125)
					gameOver(g);

				// If snake head hit bottom border
				else if (snakeYLength[i] >= 600)
					gameOver(g);
			}

			// Game over condition for hard level
			else if (level == 3)
			{
				// If snake head hit right border 
				if (snakeXLength[i] > 800)
					gameOver(g);

				// If snake head hit left border
				else if (snakeXLength[i] < 75)
					gameOver(g);

				// If snake head hit top border
				else if (snakeYLength[i] < 125)
					gameOver(g);

				// If snake head hit bottom border
				else if (snakeYLength[i] >= 600)
					gameOver(g);

				// if snake head hit first additional wall
				else if (snakeXLength[i] >= 280 && snakeXLength[i] <= 550 && snakeYLength[i] == 250)
					gameOver(g);

				// if snake head hit first additional wall
				else if (snakeXLength[i] >= 280 && snakeXLength[i] <= 550 && snakeYLength[i] == 450)
					gameOver(g);

			}

		}

		g.dispose();
	}

	// Method to change snake color based on level that user choose
	private void snakeColor(int level){
		switch (level){

		// If user choose easy 
		case 1: {
			headLeft = new ImageIcon(Constant.LEFT_GREEN);
			headUp = new ImageIcon(Constant.UP_GREEN);
			headRight = new ImageIcon(Constant.RIGHT_GREEN);
			headDown = new ImageIcon(Constant.DOWN_GREEN);
			tail = new ImageIcon(Constant.TAIL_GREEN);
			scoreLevel = 5;
			break;
		}

		// If user choose medium
		case 2: {
			headLeft = new ImageIcon(Constant.LEFT_BLUE);
			headUp = new ImageIcon(Constant.UP_BLUE);
			headRight = new ImageIcon(Constant.RIGHT_BLUE);
			headDown = new ImageIcon(Constant.DOWN_BLUE);
			tail = new ImageIcon(Constant.TAIL_BLUE);
			scoreLevel = 10;
			break;
		}

		// If user choose hard
		case 3: {
			headLeft = new ImageIcon(Constant.LEFT_RED);
			headUp = new ImageIcon(Constant.UP_RED);
			headRight = new ImageIcon(Constant.RIGHT_RED);
			headDown = new ImageIcon(Constant.DOWN_RED);
			tail = new ImageIcon(Constant.TAIL_RED);
			scoreLevel = 15;
			break;
		}
		
		}
	}

	private void gameOver(Graphics g) {
		// play sound if gameover
		soundGameOver.setFile(Constant.MUSIC_GAMEOVER);
		soundGameOver.play();

		// Snake cant move anywhere else
		right = false;
		left = false;
		up = false;
		down = false;

		// call checkscore method to check the high score
		checkScore();

		// Display game over message and final score
		g.setColor(Color.RED);
		g.setFont(new Font("arial", Font.BOLD, 40));
		g.drawString("Game Over! Score: " + score, 250, 300);

		// Display high Score 
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("HighScore: " + highScore,360,250);

		// Display press enter to restart the game
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Press Enter to restart", 350, 340);

		// Display press esc to select level
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Press Esc to select leve", 350, 370);

		g.dispose();
	}

	private void mediumLevelWall(Graphics g)
	{
		ImageIcon wall = new ImageIcon(Constant.WALL);

		// Top border wall
		for (int x = 25; x <= 850; x += 25)
			wall.paintIcon(this,g,x,74);

		// Bottom border wall
		for (int x = 25; x <= 850; x += 25)
			wall.paintIcon(this,g,x,625);

		// Left border wall
		for (int y = 75; y <= 600; y += 25)
			wall.paintIcon(this,g,25,y);

		// right border wall
		for (int y = 75; y <= 600; y += 25)
			wall.paintIcon(this,g,850,y);
	}

	private void hardLevelWall(Graphics g)
	{
		ImageIcon wall = new ImageIcon(Constant.WALL);

		// Top border wall
		for (int x = 25; x <= 850; x += 25)
			wall.paintIcon(this,g,x,74);

		// Bottom border wall
		for (int x = 25; x <= 850; x += 25)
			wall.paintIcon(this,g,x,625);

		// Left border wall
		for (int y = 75; y <= 600; y += 25)
			wall.paintIcon(this,g,25,y);

		// right border wall
		for (int y = 75; y <= 600; y += 25)
			wall.paintIcon(this,g,850,y);

		// first additional wall
		for (int x = 300; x <= 550; x += 25)
			wall.paintIcon(this,g,x,250);

		// second additional wall
		for (int x = 300; x <= 550; x += 25)
			wall.paintIcon(this,g,x,450);
	}

	private String getHighScore() {
		FileReader readFile = null;
		BufferedReader reader = null;
		try{
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		catch (Exception e){
			return "Nobody:0";
		}
		finally
		{
			try {
				if(reader != null) reader.close();
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	// Method to check if current score is higher than the highscore
	public void checkScore()
	{
		if (highScore.equals(""))
			return;
		// Because high score value is (name):(score), we split the string to two parts with ":"
		// We need the second part of it so we can compare it to the current score
		if (score > Integer.parseInt((highScore.split(":")[1])))
		{
			// User has set a new record
			String name = JOptionPane.showInputDialog("You set a new highscore. What is your name?");
			highScore = name + ":" + score;

			File scoreFile = new File("highscore.dat");
			if (!scoreFile.exists())
			{
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try
			{
				writeFile = new FileWriter(scoreFile);
				writer    = new BufferedWriter(writeFile);
				writer.write(this.highScore);
			}
			catch (Exception e)
			{
				// If errors occurs
			}
			finally
			{
				try {
					if (writer != null) 
						writer.close();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {

		timer.restart();

		// Snake move right 
		if(right) 
		{
			for(int n = lengthofSnake-1; n>=0;n--)
			{
				snakeYLength[n+1] = snakeYLength[n];
			}
			for(int n = lengthofSnake; n>=0; n--) 
			{
				if (n==0) {
					// Snake move 25 pixel to the right 
					snakeXLength[n] = snakeXLength[n]+25;
				} 
				else {
					snakeXLength[n] = snakeXLength[n-1];
				}

				// If level is easy and snake reach right border then snake will appear in left border
				if(snakeXLength[n] >850 && level == 1) {
					snakeXLength[n] = 25;
				}

			}
			repaint();
		}

		// Snake move left
		if(left) 
		{
			for(int n = lengthofSnake-1; n>=0;n--)
			{
				snakeYLength[n+1] = snakeYLength[n];
			}
			for(int n = lengthofSnake; n>=0; n--) 
			{
				if (n==0) {
					// Snake move 25 pixel to the left
					snakeXLength[n] = snakeXLength[n]-25;
				} 
				else {
					snakeXLength[n] = snakeXLength[n-1];
				}

				// If level is easy and snake reach left border then snake will appear in right border
				if(snakeXLength[n] < 25 && level == 1) {
					snakeXLength[n] = 850;
				}


			}
			repaint();			
		}

		// Snake move up
		if(up) 
		{
			for(int n = lengthofSnake-1; n>=0;n--)
			{
				snakeXLength[n+1] = snakeXLength[n];
			}

			for(int n = lengthofSnake; n>=0; n--) 
			{
				if (n==0) 
				{
					// Snake move 25 pixel to up
					snakeYLength[n] = snakeYLength[n]-25;
				} 
				else 
				{
					snakeYLength[n] = snakeYLength[n-1];
				}

				// If level is easy and snake reach top border then snake will appear in bottom border
				if(snakeYLength[n] < 75 && level == 1) {
					snakeYLength[n] = 625;
				}


			}
			repaint();

		}

		// Snake move down
		if(down) 
		{
			for(int n = lengthofSnake-1; n>=0;n--)
			{
				snakeXLength[n+1] = snakeXLength[n];
			}

			for(int n = lengthofSnake; n>=0; n--) {
				if (n==0) 
				{
					// Snake move 25 pixel to bottom
					snakeYLength[n] = snakeYLength[n]+25;
				} 
				else 
				{
					snakeYLength[n] = snakeYLength[n-1];
				}

				// If level is easy and snake reach bottom border then snake will appear in top border and
				if(snakeYLength[n] > 625 && level == 1) {
					snakeYLength[n] = 75;
				}
			}

			repaint();
		}


	}

	@Override
	public void keyPressed(KeyEvent e) {

		// Restart the game if enter button is pressed
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			moves = 0;
			score = 0;
			lengthofSnake=3;
			repaint();
		}

		// Snake move right if right keypad button is pressed
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left) 
			{
				right = true;
			}
			else 
			{
				// If the snake was heading to the left then it can't immediately turn right
				right = false;
				left = true;
			}

			up = false;
			down = false;


		}

		// Snake move left if left keypad button is pressed
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right) 
			{
				left = true;
			}
			else
			{
				// If the snake was heading to the right then it can't immediately turn left
				left = false;
				right = true;
			}

			up = false;
			down = false;


		}

		// Snake move up if up keypad button is pressed
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else
			{
				// If the snake was heading down then it can't immediately heading up
				up = false;
				down = true;
			}

			left = false;
			right = false;


		}

		// Snake move down if down keypad button is pressed
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else
			{
				// If the snake was heading up then it can't immediately heading down
				up = true;
				down = false;

			}

			left = false;
			right = false;


		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			Level level = new Level();
			Functions.frame(level);
			Functions.frame(level);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}
}