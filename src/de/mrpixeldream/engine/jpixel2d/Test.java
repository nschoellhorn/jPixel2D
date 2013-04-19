package de.mrpixeldream.engine.jpixel2d;

import java.awt.Graphics;
import java.awt.Point;

public class Test extends GameApp
{
	private static final long	serialVersionUID	= 1L;

	public Test(int width, int height, String title, Point loc)
	{
		super(width, height, title, loc);
	}

	public static void main(String[] args)
	{
		new Test(800, 600, "Test-App", new Point(100, 100));
	}

	@Override
	protected void doLogic()
	{
		
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.clearRect(10, 10, 80, 20);
		g.drawString(Long.toString(fps), 10, 10);
	}

	@Override
	protected void gameLost()
	{
		System.out.println("GEWONNEN!");
	}

	@Override
	protected void gameWon()
	{
		System.out.println("VERLOREN!");
	}
}