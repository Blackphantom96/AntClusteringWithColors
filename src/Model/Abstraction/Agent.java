package Model.Abstraction;

public interface Agent {
	void iterate();

	void move();

	int getPosX();

	int getPosY();

	Particle<?> getParticle();

	public boolean hasPayload();

}
