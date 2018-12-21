package Model.Impl.Color;

import Model.Abstraction.*;

public class ColorPopulation extends Population<int[]> {
	public ColorPopulation(int population) throws Exception {
		super(ColorAgent.class, population);
	}

}
