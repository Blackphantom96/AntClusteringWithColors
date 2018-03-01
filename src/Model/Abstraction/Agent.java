package Model.Abstraction;


public interface Agent {
	void iterate();

	int[] move();

	int getPosX();

	int getPosY();

	Particle<?> getParticle();

	public boolean hasPayload();
	
	void setPosX(int x);
	
	void setPosY(int y);

	void setParticle(Particle<?> tempParticle);

}
