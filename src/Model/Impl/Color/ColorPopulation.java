package Model.Impl.Color;

import Model.Abstraction.*;
import Model.Impl.CoreFactoryCreator;

public class ColorPopulation implements Population {

	private ColorAgent[][] agents;

	/*
	 * FIXME porque se inicaliza toda la matriz en colores? tengo entendido que no
	 * necesariamente toda la matriz debe contener colores, pueden haber espacios en
	 * blanco. Ademas, ud esta colocando un ColorAgent en cada posicion ij-esima,
	 * pero en el constructor de ColorAgent el mismo se coloca una "posicion"
	 * aleatoria, entonces esta creando una matriz con agentes que se colocan una
	 * posicion aleatoria distinta a la ij-esima.
	 */
	public ColorPopulation(int population) {
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		agents = new ColorAgent[maxX][maxY];
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				agents[i][j] = new ColorAgent();
			}
		}
	}

	@Override
	public void update() {
		int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				agents[i][j].iterate();
			}
		}
	}

	@Override
	public Agent[][] getAgents() {
		return agents;
	}

}
