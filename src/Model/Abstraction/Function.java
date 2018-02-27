package Model.Abstraction;

public interface Function<E> {
	int probPick(int x, int y, Particle<E> p) ;
	int probDeposit(int x, int y, Particle<E> p) ;
	double itemPerceivedFraction(int x, int y, int r, Particle<E> p);
}
