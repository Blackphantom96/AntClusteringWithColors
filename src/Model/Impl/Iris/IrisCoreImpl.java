package Model.Impl.Iris;

import Model.Abstraction.Core;
import Model.Abstraction.Particle;
import Model.Abstraction.Population;

import java.util.Random;

public class IrisCoreImpl implements Core<int[]> {

    private IrisPopulation population;
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


    public IrisCoreImpl(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r, double alpha) {
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
    public int getMaxX() {
        return sizeX;
    }

    @Override
    public int getMaxY() {
        return sizeY;
    }

    @Override
    public Population<int[]> getPopulation() {
        return population;
    }

    @Override
    public Particle<int[]>[][] getParticles() {
        return new Particle[0][];
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

    }

    @Override
    public double getAlpha() {
        return alpha;
    }

    @Override
    public void createPopulation() {

    }
}
