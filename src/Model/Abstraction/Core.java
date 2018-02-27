package Model.Abstraction;

public class Core {

	private static Core instance;
	
	/* ---------------------- */

	private final int population;
	private final int particles;
	private final double k1;
	private final double k2;
	private final int sizeX;
	private final int sizeY;

	private Core(int population, int particles, double k1, double k2, int sizeX, int sizeY) {
		this.population = population;
		this.particles = particles;
		this.k1 = k1;
		this.k2 = k2;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public static Core getInstance() {
		if (instance == null) {
			throw new NullPointerException("Instancia de Core no fue inicializada.");
		}
		return instance;
	}

	public static Core createInstance(int population, int particles, double k1, double k2, int sizeX, int sizeY) {
		instance = new Core(population, particles, k1, k2, sizeX, sizeY);
		return instance;
	}

	public int getMaxX() {
		return sizeX;
	}

	public int getMaxY() {
		return sizeY;
	}

	public int getPopulation() {
		return population;
	}

	public int getParticles() {
		return particles;
	}

	public double getK1() {
		return k1;
	}

	public double getK2() {
		return k2;
	}
}
