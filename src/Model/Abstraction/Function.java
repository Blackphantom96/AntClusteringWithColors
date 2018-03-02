package Model.Abstraction;

public interface Function<E> {
	int probPick(int x, int y, Particle<E> p);

	int probDeposit(int x, int y, Particle<E> p);

	double itemAverageSimilarity(int x, int y, int r, Particle<E> p);
	
	/**
	 * La forma de solucionar depende del problema a tratar (Ref pag 228)
	 * @param x posicion x
	 * @param y posicion y
	 * @param r radio de percepcion
	 * @param p particula
	 * @return fraccion de percepcion de la particula p
	 */
}
