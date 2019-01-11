package Model.Impl.Color;

import Model.Abstraction.Colony;

public class ColorColony extends Colony<int[]> {
	public ColorColony(int population) throws Exception {
		super(ColorAnt.class, population);
	}

}
