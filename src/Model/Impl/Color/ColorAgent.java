package Model.Impl.Color;

import java.util.*;

import Model.Abstraction.*;
import Model.Impl.CoreFactoryCreator;

public class ColorAgent implements Agent {

	private Random rand = new Random();
	private int posX, posY;
	private int maxX, maxY;
	private ColorParticle particle;

	public ColorAgent(int x,int y) {
		this.maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		this.maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		posX = x;
		posY = y;
	}

	@Override
	public boolean hasPayload() {
		return particle != null;
	}

	@Override
	public ColorParticle getParticle() {
		return particle;
	}

	@Override
	public int getPosX() {
		return posX;
	}

	@Override
	public int getPosY() {
		return posY;
	}

	@Override
	public void iterate() {
		move();
	}

	@Override
	public int[] move() { 
		int velX = rand.nextInt(3) - 1; 
		int velY = rand.nextInt(3) - 1;
		while ( (posX + velX) < 0 || (posX + velX) >= maxX)
			velX = rand.nextInt(3) - 1;
		while ((posY + velY) < 0 || (posY + velY) >= maxY) {
			velY = rand.nextInt(3) - 1;
		}
		return new int [] {posX+velX,posY+velY}; 
	}

	@Override
	public void setPosX(int x) {
		posX = x;
	}

	@Override
	public void setPosY(int y) {
		posY = y;
	}
	
	@Override
	public String toString() {
		return "*";
	} 
	
	@Override
	public void setParticle(Particle<?> tempParticle) {
		particle = (ColorParticle) tempParticle;
	}
}
