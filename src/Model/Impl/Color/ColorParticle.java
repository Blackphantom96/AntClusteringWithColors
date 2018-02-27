package Model.Impl.Color;

import java.util.*;

import Model.Abstraction.*;

public class ColorParticle implements Particle<List<Integer>> {

	private int posX, posY;
	private static Random rand = new Random();
	private ArrayList<Integer> properties;

	public ColorParticle() {
		this(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
	}

	public ColorParticle(int r, int g, int b) {
		posX = rand.nextInt(Core.getInstance().getMaxX());
		posY = rand.nextInt(Core.getInstance().getMaxY());
		properties = new ArrayList<>(Arrays.asList(r, g, b));
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
	public List<Integer> getProperties() {
		return properties;
	}

	@Override
	public double euclideanDistance(Particle<List<Integer>> p) {
		int sum1 = 0;
		for (int i = 0; i < 3; i++)
			sum1 += (properties.get(i) - p.getProperties().get(i)) * (properties.get(i) - p.getProperties().get(i));
		return Math.sqrt(sum1);
	}
}
