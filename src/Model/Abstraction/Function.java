package Model.Abstraction;

public interface Function {
	int probP(int x, int y) ;
	int probD(int x, int y) ;
	double visionFunction(int x, int y, int r, Particle<?> p);
	//TODO poner getters y setters
}
