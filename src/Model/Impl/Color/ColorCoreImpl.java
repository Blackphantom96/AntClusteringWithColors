package Model.Impl.Color;

import java.util.*;

import Model.Abstraction.Core;
import Model.Abstraction.Particle;

public class ColorCoreImpl implements Core<int[]> {

	private ColorPopulation population;
	///private final List<Particle<int[]>> particles; // XXX toca mirar Juan: que es eso ¿?
	private final double k1;
	private final double k2;
	private final int sizeX;
	private final int sizeY;
	private final int r;
	private final int populationSize;
	private final int particleSize;
	private final double alpha;
	private Random rand = new Random();
	private Particle<int[]>[][] grid;
	

	public ColorCoreImpl(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r, double alpha) {
		///this.particles = null; // TODO falta
		this.k1 = k1;
		this.k2 = k2;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.r = r;
		this.populationSize = population;
		this.particleSize = particles;
		this.alpha = alpha;
		this.population = null;
	}
	
	@Override
	public double getAlpha() {
		return alpha;
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
	public ColorPopulation getPopulation() {
		return population;
	}

	@Override
	public Particle<int[]>[][] getParticles() {
		return grid; // TODO terminar
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
		return populationSize;
	}

	@Override
	public int getParticleSize() {
		return particleSize;
	}

	@Override
	public void generateParticleMatrix() {
		int particleSizeCopy = particleSize; 
		grid =new ColorParticle[sizeX][sizeY];
		while(particleSizeCopy!=0) {
			int x=rand.nextInt(sizeX);
			int y=rand.nextInt(sizeY);
			if(grid[x][y]==null) {
				grid[x][y]=new ColorParticle();
				particleSizeCopy--;
			}
		}
	}

	@Override
	public void createPopulation() {
		try {
			population = new ColorPopulation(populationSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
