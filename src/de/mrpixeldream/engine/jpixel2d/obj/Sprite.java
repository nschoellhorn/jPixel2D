package de.mrpixeldream.engine.jpixel2d.obj;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D.Double;
import java.awt.image.BufferedImage;

import de.mrpixeldream.engine.jpixel2d.GameApp;
import de.mrpixeldream.engine.jpixel2d.util.Drawable;
import de.mrpixeldream.engine.jpixel2d.util.Movable;

public abstract class Sprite extends Double implements Drawable, Movable
{
	private static final long	serialVersionUID	= 1L;
	
	long delay;
	long animation = 0;
	
	GameApp parent;
	
	BufferedImage[] pics;
	int currentpic = 0;
	
	int x;
	int y;
	
	int width;
	int height;
	
	protected int dx;
	protected int dy;
	
	public boolean remove = false;

	public Sprite(BufferedImage[] anim, int x, int y, long delay, GameApp parent)
	{
		this.pics = anim;
		this.x = x;
		this.y = y;
		this.delay = delay;
		this.width = this.pics[0].getWidth();
		this.height = this.pics[0].getHeight();
		this.parent = parent;
	}
	
	private void computeAnimation()
	{
		currentpic++;
		
		if (currentpic >= pics.length)
		{
			currentpic = 0;
		}
	}
	
	public int getHorizontalSpeed()
	{
		return dx;
	}

	public void setHorizontalSpeed(int dx)
	{
		this.dx = dx;
	}

	public int getVerticalSpeed()
	{
		return dy;
	}

	public void setVerticalSpeed(int dy)
	{
		this.dy = dy;
	}

	@Override
	public void doLogic(long delta)
	{
		animation += (delta / 1000000);
		if (animation > delay)
		{
			animation = 0;
			computeAnimation();
		}
	}

	@Override
	public void move(long delta)
	{
		if (dx != 0)
		{
			x += dx * (delta / 1E9);
		}
		if (dy != 0)
		{
			y += dy * (delta / 1E9);
		}
	}

	@Override
	public void drawObjects(Graphics g)
	{
		if (x <= parent.getWidth() && y <= parent.getHeight())
		{
			g.drawImage(pics[currentpic], x, y, null);
		}
		else
		{
			remove = true;
		}
	}
}
