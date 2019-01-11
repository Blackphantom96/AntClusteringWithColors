package Model.Impl.Color;

import Model.Abstraction.Ant;
import Model.Abstraction.Core;
import Model.Abstraction.Function;
import Model.Abstraction.Item;

import java.util.Random;

public class ColorCoreImpl implements Core<int[]> {

	private ColorColony population;
	///private final List<VisualItem<int[]>> particles; // XXX toca mirar Juan: que es eso ¿?
	private final double k1;
	private final double k2;
	private final int sizeX;
	private final int sizeY;
	private final int r;
	private final int populationSize;
	private final int particleSize;
	private final double alpha;
	private Random rand = new Random();
	private Item<int[]>[][] grid;
	private Function functions = new ColorFunction();


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
	public ColorColony getPopulation() {
		return population;
	}

	@Override
	public Item<int[]>[][] getParticles() {
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
		grid = new ColorItem[sizeX][sizeY];
		while (particleSizeCopy != 0) {
			int x = rand.nextInt(sizeX);
			int y = rand.nextInt(sizeY);
			if (grid[x][y] == null) {
				grid[x][y] = new ColorItem();
				particleSizeCopy--;
			}
		}
	}

	@Override
	public void createPopulation() {
		try {
			population = new ColorColony(populationSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void iterate() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				Ant tempAgent = this.getPopulation().getAnts()[i][j];
				Item tempParticle = (Item) this.getParticles()[i][j];
				if (tempAgent != null) {
					if (!tempAgent.hasPayload() && tempParticle != null) {
						int pp = functions.probPick(i, j, tempParticle);
						if (rand.nextInt(100) + 1 < pp) {
							tempAgent.setItem(tempParticle);
							this.getParticles()[i][j] = null;
						}
					} else if (tempAgent.hasPayload() && tempParticle == null) {
						int pd = functions.probDeposit(i, j, tempAgent.getItem());
						// System.out.println(pd);
						if (rand.nextInt(100) + 1 < pd) {
							this.getParticles()[i][j] = tempAgent.getItem();
							tempAgent.setItem(null);
						}
					}
				}
				this.getPopulation().move(i, j);
			}
		}
	}
}