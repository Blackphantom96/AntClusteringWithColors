package Model.Abstraction;

public interface Population {
	void move(int i,int j);

	Agent[][] getAgents();

	String toString();
	
	
}
