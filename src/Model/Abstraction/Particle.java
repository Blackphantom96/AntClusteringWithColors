package Model.Abstraction;

public interface Particle<E> {
	E getProperties() ;
	double euclideanDistance(Particle<E> p);
	//TODO poner getters y setters
}
