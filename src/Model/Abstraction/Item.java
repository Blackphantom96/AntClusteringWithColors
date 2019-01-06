package Model.Abstraction;

public interface Item<E> {
	E getProperties();

	double distance(Item<E> p);

	int getPosX();

	int getPosY();
	
	String toString();
}
