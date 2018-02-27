package Model.Abstraction;

public interface Particle<E> {
	E getProperties() ;
	double euclideanDistance(Particle<E> p);
	int getPosX();
	int getPosY();
}
