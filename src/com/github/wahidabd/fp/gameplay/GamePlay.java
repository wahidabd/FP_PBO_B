package com.github.wahidabd.fp.gameplay;

import com.github.wahidabd.fp.utils.Constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private final int[] snakeXLength = new int[750];
	private final int[] snakeYLength = new int[750];
	private final int level;
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private ImageIcon headRight;
	private ImageIcon headLeft;
	private ImageIcon headUp;
	private ImageIcon headDown;
	
	private int lengthofsnake = 3;
	
	private final Timer timer;

	private ImageIcon tail;
	private int moves = 0;
	private int score = 0;


	private final int [] fruitXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,250,375,400,425,450,475,500,525,550,575,600,
			625,650,675,700,725,750,775,800,825,850};
	
	private final int [] fruitYpos = {75,100,125,150,175,200,225,250,275,300,325,250,375,400,425,450,475,500,525,550,575,600,
			625};


	private final Random random = new Random();
	
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	//Part5
	
	
	
	//Part2


	public GamePlay(int delay, int level) {
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		this.level = level;

		timer = new Timer(delay, this);
		timer.start();
	}


	public void paint(Graphics g) {
		
		if (moves == 0)
		{
			snakeXLength[2] = 50;
			snakeXLength[1] = 75;
			snakeXLength[0] = 100;

			snakeYLength[2] = 100;
			snakeYLength[1] = 100;
			snakeYLength[0] = 100;
			
		}
		

		
		//draw title image
		ImageIcon titleImage = new ImageIcon(Constant.TITLE_IMAGE);
		titleImage.paintIcon(this, g, 25, 5);
		
		
	    //draw border for gameplay
		g.setColor(Color.DARK_GRAY);
		g.drawRect(24, 74, 851, 577);
		
		//background for the gameplay
		g.setColor(Color.black);
		g.fillRect(25,75,850,575);
		
		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("areal", Font.PLAIN, 14));
		g.drawString("Scores: " + score, 780, 30);
		
		//draw length snake
		g.setColor(Color.white);
		g.setFont(new Font("areal", Font.PLAIN, 14));
		g.drawString("Length: " + lengthofsnake, 780, 50);
		
		//initial position
		snakeColor(level);
		headRight.paintIcon(this,g , snakeXLength[0], snakeYLength[0]);
		
		for(int i = 0; i< lengthofsnake; i++) {
			if(i == 0 && right) headRight.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i == 0 && left) headLeft.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i == 0 && down) headDown.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i == 0 && up) headUp.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

			if(i != 0) tail.paintIcon(this,g , snakeXLength[i], snakeYLength[i]);

		}
		//Part3
		//Part5
		ImageIcon fruitimage = new ImageIcon(Constant.FRUIT_IMAGE);
		if(fruitXpos[xpos] == snakeXLength[0] && fruitYpos[ypos] == snakeYLength[0])
		{
			score= score+5;
			lengthofsnake++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
		}
		
		fruitimage.paintIcon(this,g,fruitXpos[xpos], fruitYpos[ypos]);

		for(int i = 1; i<lengthofsnake; i++)
		{
			if(snakeXLength[i] == snakeXLength[0] && snakeYLength[i] == snakeYLength[0])
			{
				right = false;
				left = false;
				up = false;
				down = false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 40));
				g.drawString("Game Over! Score: " + score, 250, 300);

				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Enter to restart", 350, 340);
				
				
			}
		}

		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//Part4
		timer.restart();
		if(right) 
		{
			if (lengthofsnake - 1 + 1 >= 0) System.arraycopy(snakeYLength, 0, snakeYLength, 1, lengthofsnake - 1 + 1);

			for(int n = lengthofsnake; n>=0; n--) {
				if (n==0) {
					snakeXLength[n] = snakeXLength[n]+25;
				} else {
					snakeXLength[n] = snakeXLength[n-1];
				}

				if(snakeXLength[n] >850) {
					snakeXLength[n] = 25;
				}
			}
			repaint();
		}
		
		if(left) 
		{
			if (lengthofsnake - 1 + 1 >= 0) System.arraycopy(snakeYLength, 0, snakeYLength, 1, lengthofsnake - 1 + 1);
			for(int n = lengthofsnake; n>=0; n--) {
				if (n==0) {
					snakeXLength[n] = snakeXLength[n]-25;
				} else {
					snakeXLength[n] = snakeXLength[n-1];
				}


				if(snakeXLength[n] < 25) {
					snakeXLength[n] = 850;
				}
				
			}
			repaint();			
			
		}
		if(up) 
		{
			if (lengthofsnake - 1 + 1 >= 0) System.arraycopy(snakeXLength, 0, snakeXLength, 1, lengthofsnake - 1 + 1);

			for(int n = lengthofsnake; n>=0; n--) {
				if (n==0) {
					snakeYLength[n] = snakeYLength[n]-25;
				} else {
					snakeYLength[n] = snakeYLength[n-1];
				}

				if(snakeYLength[n] < 75) {
					snakeYLength[n] = 625;
				}
			}
			repaint();
			
		}
		if(down) 
		{
			if (lengthofsnake - 1 + 1 >= 0) System.arraycopy(snakeXLength, 0, snakeXLength, 1, lengthofsnake - 1 + 1);

			for(int n = lengthofsnake; n>=0; n--) {
				if (n==0) {
					snakeYLength[n] = snakeYLength[n]+25;
				} else {
					snakeYLength[n] = snakeYLength[n-1];
				}


				if(snakeYLength[n] > 625) {
					snakeYLength[n] = 75;
				}
			}
			
			repaint();
		}
		
		
	}
	//Part4

	@Override
	public void keyPressed(KeyEvent arg0) {

		if(arg0.getKeyCode() == KeyEvent.VK_ENTER)
		{
			moves = 0;
			score = 0;
			lengthofsnake=3;
			repaint();
		}
		

		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left) {
				right = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;


		}
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right) {
				left = true;
			}
			else
			{
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
			
			
		}
		if(arg0.getKeyCode() == KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!down) {
				up = true;
			}
			else
			{
				up = false;
				down = true;
			}
			
			left = false;
			right = false;
			
			
		}
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!up) {
				down = true;
			}
			else
			{
				up = true;
				down = false;
				
			}
			
			left = false;
			right = false;
			
			
		}
	}

	private void snakeColor(int level){
		switch (level){
			case 1: {
				headLeft = new ImageIcon(Constant.LEFT_GREEN);
				headUp = new ImageIcon(Constant.UP_GREEN);
				headRight = new ImageIcon(Constant.RIGHT_GREEN);
				headDown = new ImageIcon(Constant.DOWN_GREEN);
				tail = new ImageIcon(Constant.TAIL_GREEN);
				break;
			}

			case 2: {
				headLeft = new ImageIcon(Constant.LEFT_BLUE);
				headUp = new ImageIcon(Constant.UP_BLUE);
				headRight = new ImageIcon(Constant.RIGHT_BLUE);
				headDown = new ImageIcon(Constant.DOWN_BLUE);
				tail = new ImageIcon(Constant.TAIL_BLUE);
				break;
			}

			case 3: {
				headLeft = new ImageIcon(Constant.LEFT_RED);
				headUp = new ImageIcon(Constant.UP_RED);
				headRight = new ImageIcon(Constant.RIGHT_RED);
				headDown = new ImageIcon(Constant.DOWN_RED);
				tail = new ImageIcon(Constant.TAIL_RED);
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
