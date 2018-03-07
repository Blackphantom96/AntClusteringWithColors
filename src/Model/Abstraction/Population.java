package Model.Abstraction;

public interface Population<E> {
	void move(int i,int j);

	Agent<E>[][] getAgents();

	String toString();
	
	
}
