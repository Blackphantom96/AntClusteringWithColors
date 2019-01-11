package Model.Abstraction;

public interface Core<E> {
	int getMaxX();

	int getMaxY();

	Colony<E> getPopulation();

	Item<E>[][] getParticles();

	double getK1();

	double getK2();

	int getRadio();
	
	int getPopulationSize();
	
	int getParticleSize();
	
	void generateParticleMatrix();
	
	double getAlpha();
	
	void createPopulation();

	void iterate();
}
