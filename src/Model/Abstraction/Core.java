package Model.Abstraction;

public interface Core<E> {
	public int getMaxX();

	public int getMaxY();

	public Population getPopulation();

	public Particle<E>[] getParticles();

	public double getK1();

	public double getK2();

	public int getRadio();
}
