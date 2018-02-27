package Model.Impl.Color.Factory;

import Model.Abstraction.Core;
import Model.Abstraction.Factory.*;
import Model.Impl.Color.ColorCoreImpl;

public class ColorCoreImplFactory implements CoreFactory {

	private Core coreInstance;
	
	public Core getInstance() {
		if (coreInstance == null) {
			throw new NullPointerException("Instancia de Core no fue creada.");
		}
		return coreInstance;
	}
	
	public Core createInstance(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r) {
		coreInstance = new ColorCoreImpl(population, particles, k1, k2, sizeX, sizeY, r);
		return coreInstance;
	}
	
}
