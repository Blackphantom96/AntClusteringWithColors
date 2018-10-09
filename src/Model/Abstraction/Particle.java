package Model.Abstraction;

public interface Particle<E> {
	E getProperties();

	double distance(Particle<E> p);

	int getPosX();

	int getPosY();
	
	String toString();
}
