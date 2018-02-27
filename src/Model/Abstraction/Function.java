package Model.Abstraction;

public interface Function<E> {
	int probPick(int x, int y) ;
	int probDeposit(int x, int y) ;
	double itemPerceivedFraction(int x, int y, int r, Particle<E> p);
}
