package de.mrpixeldream.engine.jpixel2d;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.mrpixeldream.engine.jpixel2d.controls.Controller;
import de.mrpixeldream.engine.jpixel2d.controls.KeyController;
import de.mrpixeldream.engine.jpixel2d.sound.SoundLib;

public abstract class GameApp extends JPanel implements Runnable
{
	private static final long serialVersionUID	= 1L;
	
	JFrame gameFrame;
	
	long delta = 0;
	long last = 0;
	long fps = 0;
	
	boolean gameover = false;
	boolean win = false;
	
	SoundLib soundLib;
	
	public GameApp(int width, int height, String title, Point loc)
	{
		this.setPreferredSize(new Dimension(width, height));
		
		gameFrame = new JFrame(title);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLocation(loc);
		gameFrame.add(this);
		gameFrame.pack();
		gameFrame.setVisible(true);
		
		init();
		
		Thread game = new Thread(this);
		game.start();
	}
	
	private void init()
	{
		last = System.nanoTime();
		
		soundLib = new SoundLib();
	}
	
	private void computeDelta()
	{
		delta = System.nanoTime() - last;
		last = System.nanoTime();
		fps = ((long) 1E9) / delta;
	}
	
	public final void addController(Controller c)
	{
		if (c instanceof KeyController)
		{
			this.addKeyListener(c);
		}
	}
	
	protected abstract void gameLost();
	
	protected abstract void gameWon();
	
	protected abstract void doLogic();
	
	@Override
	public abstract void paintComponent(Graphics g);
	
	@Override
	public void run()
	{
		while (gameFrame.isVisible())
		{
			computeDelta();
			
			if (!win && !gameover)
			{
				doLogic();
			}
			else
			{
				if (win)
				{
					gameWon();
				}
				else if (gameover)
				{
					gameLost();
				}
			}
			
			repaint();
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}