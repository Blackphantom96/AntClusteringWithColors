package Model.Impl.Color;

import java.util.*;

import Model.Abstraction.*;
import Model.Impl.CoreFactoryCreator;
import utiles.LAB;

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
		properties = new int[] { r, g, b };
		/*
		int c = 0;
		while(c!=2) {
			int x = rand.nextInt(3);
			if(properties[x]!=0) {
				properties[x]*=0;
				c++;
			}
		
		}*/
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
	public double distance(Particle<int[]> p) {
		int sum1 = 0;
		LAB lab1 = LAB.fromRGB(properties[0], properties[1], properties[2], 1);
		LAB lab2 = LAB.fromRGB(p.getProperties()[0], p.getProperties()[1], p.getProperties()[2], 1);
		System.err.println(LAB.ciede2000(lab1, lab2));
		return LAB.ciede2000(lab1, lab2);
	}

	@Override
	public String toString() {
		if (properties[0] > properties[1] && properties[0] > properties[2])
			return "R";
		else if (properties[1] > properties[0] && properties[1] > properties[2])
			return "G";
		else if (properties[2] > properties[1] && properties[2] > properties[1])
			return "B";
		else
			return "C";
	}
	
}
