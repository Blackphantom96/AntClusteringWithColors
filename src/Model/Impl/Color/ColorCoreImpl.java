package Model.Impl.Color;

import java.util.List;
import Model.Abstraction.Core;
import Model.Abstraction.Particle;
import Model.Abstraction.Population;

public class ColorCoreImpl implements Core<List<Integer>> {

	private final Population population;
	private final List<Particle<List<Integer>>> particles; // XXX toca mirar
	private final double k1;
	private final double k2;
	private final int sizeX;
	private final int sizeY;
	private final int r;
	private final int populationSize;
	private final int particleSize;
	
	
	private Particle<List<Integer>>[][] grid;

	public ColorCoreImpl(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r) {
		this.population = new ColorPopulation(population);
		this.particles = null; // TODO falta
		this.k1 = k1;
		this.k2 = k2;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.r = r;
		this.populationSize = population;
		this.particleSize = particles;
	}

	@Override
	public int getMaxX() {
		return sizeX;
	}

	@Override
	public int getMaxY() {
		return sizeY;
	}

	@Override
	public Population getPopulation() {
		return population;
	}

	@Override
	public Particle<List<Integer>>[] getParticles() {
		return null; // TODO terminar
	}

	@Override
	public double getK1() {
		return k1;
	}

	@Override
	public double getK2() {
		return k2;
	}

	@Override
	public int getRadio() {
		return r;
	}

	@Override
	public int getPopulationSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getParticleSize() {
		// TODO Auto-generated method stub
		return 0;
	}
}
