package Model.Impl;

import java.util.*;

import Model.Abstraction.Agent;

public class ColorAgent implements Agent{
	
	private Random rand = new Random();
	private int posX,posY ;
	private int maxX, maxY;
	public ColorAgent(int maxX, int maxY) {
		this.maxX= maxX;
		this.maxY = maxY;
		posX = rand.nextInt(maxX);
		posY = rand.nextInt(maxY);
	}
	
	
	@Override
	public void iterate() {
		move();
	}

	@Override
	public void move() {
		int velX = rand.nextInt(2)-1;
		int velY = rand.nextInt(2)-1;
		while(posX+velX<0 || posX+velX>maxX)
			velX = rand.nextInt(2)-1;
		while(posY+velY<0 || posX+velY>maxY)
			velY = rand.nextInt(2)-1;
		posX += velX;
		posY += velY;
	}
	
}
