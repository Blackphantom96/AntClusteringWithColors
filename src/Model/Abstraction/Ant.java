package Model.Abstraction;


public interface Ant<E> {
	void iterate();

	int[] move();

	int getPosX();

	int getPosY();

	Item<E> getItem();

	public boolean hasPayload();
	
	void setPosX(int x);
	
	void setPosY(int y);

	void setItem(Item<E> tempItem);

}
