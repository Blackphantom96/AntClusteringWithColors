package Model.Impl.Color.Factory;

import Model.Abstraction.Factory.*;
import Model.Impl.Color.ColorCoreImpl;

public class ColorCoreImplFactory implements CoreFactory {

	private ColorCoreImpl coreInstance;
	
	public ColorCoreImpl getInstance() {
		if (coreInstance == null) {
			throw new NullPointerException("Instancia de Core no fue creada.");
		}
		return coreInstance;
	}
	
	public ColorCoreImpl createInstance(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r,double alpha) {
		coreInstance = new ColorCoreImpl(population, particles, k1, k2, sizeX, sizeY, r, alpha);
		return coreInstance;
	}
	
}
