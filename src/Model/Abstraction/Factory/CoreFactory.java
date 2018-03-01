package Model.Abstraction.Factory;

import Model.Abstraction.Core;

public interface CoreFactory {

	public Core<?> getInstance();

	public Core<?> createInstance(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r,double alpha);
}
