package Model.Impl.Color;

import java.util.*;

import Model.Abstraction.*;
import Model.Impl.CoreFactoryCreator;

public class ColorParticle implements Particle<int[]> {

	private static final int COLOR_DEPTH = 256;

	private int posX, posY;
	private static Random rand = new Random();
	private int[] properties;

	public ColorParticle() {
		this(rand.nextInt(COLOR_DEPTH), rand.nextInt(COLOR_DEPTH), rand.nextInt(COLOR_DEPTH));
	}

	public ColorParticle(int r, int g, int b) {
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		posX = rand.nextInt(maxX);
		posY = rand.nextInt(maxY);
		properties = new int[] {r,g,b};
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
	public int[] getProperties() {
		return properties;
	}

	@Override
	public double euclideanDistance(Particle<int[]> p) {
		int sum1 = 0;
		for (int i = 0; i < 3; i++)
			sum1 += Math.pow(properties[i] - p.getProperties()[i], 2);
		return Math.sqrt(sum1);
	}
}
