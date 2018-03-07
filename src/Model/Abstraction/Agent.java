package Model.Abstraction;


public interface Agent<E> {
	void iterate();

	int[] move();

	int getPosX();

	int getPosY();

	Particle<E> getParticle();

	public boolean hasPayload();
	
	void setPosX(int x);
	
	void setPosY(int y);

	void setParticle(Particle<E> tempParticle);

}
