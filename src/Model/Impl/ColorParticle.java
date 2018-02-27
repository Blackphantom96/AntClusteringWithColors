package Model.Impl;

import java.util.*;

import Model.Abstraction.*;

public class ColorParticle implements Particle<List<Integer>>{

	private int posX, posY ;
	private Random rand = new Random();
	private ArrayList<Integer> properties;
	public ColorParticle() {
		posX = rand.nextInt(Core.getMaxX());
		posY = rand.nextInt(Core.getMaxY());
		properties = new ArrayList<>();
		properties.add(rand.nextInt(256));
		properties.add(rand.nextInt(256));
		properties.add(rand.nextInt(256));
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	@Override
	public List<Integer> getProperties() {
		return properties;
	}

	@Override
	public double euclideanDistance(Particle<List<Integer>> p) {
		int sum1 =0;
		for(int i=0 ;i<3 ;i++)
			sum1 += (properties.get(i) - p.getProperties().get(i)) * (properties.get(i) - p.getProperties().get(i));
		return Math.sqrt(sum1);
	}
}
