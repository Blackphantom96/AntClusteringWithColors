package Model.Impl.Color;

import Model.Abstraction.Item;
import Model.Impl.CoreFactoryCreator;
import utiles.LAB;

import java.util.Random;

public class ColorItem implements Item<int[]> {

	private static final int COLOR_DEPTH = 256;

	private int posX, posY;
	private static Random rand = new Random();
	private int[] properties;
	private String realClass;

	public ColorItem() {
		this(rand.nextInt(COLOR_DEPTH), rand.nextInt(COLOR_DEPTH), rand.nextInt(COLOR_DEPTH));
	}

	public ColorItem(int r, int g, int b) {
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		posX = rand.nextInt(maxX);
		posY = rand.nextInt(maxY);
		properties = new int[] { r, g, b };

		if (r > g && r > b) {
			realClass = "RED";
		} else if (g > r && g > b) {
			realClass = "GREEN";
		} else if (b > r && b > g) {
			realClass = "BLUE";
		} else if (r == b && r == g) {
			if (r <= 100) {
				realClass = "BLACK";
			} else {
				realClass = "WHITE";
			}
		} else {
			realClass = "MAGENTA"; // significa que esta mal
		}
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
	public double distance(Item<int[]> p) {
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

	@Override
	public String getRealClass() {
		return realClass;
	}

}
