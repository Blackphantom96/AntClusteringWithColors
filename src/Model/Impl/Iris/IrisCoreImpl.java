package Model.Impl.Iris;

import Model.Abstraction.Core;
import Model.Abstraction.Particle;
import Model.Abstraction.Population;

public class IrisCoreImpl implements Core<int[]> {
    @Override
    public int getMaxX() {
        return 0;
    }

    @Override
    public int getMaxY() {
        return 0;
    }

    @Override
    public Population<int[]> getPopulation() {
        return null;
    }

    @Override
    public Particle<int[]>[][] getParticles() {
        return new Particle[0][];
    }

    @Override
    public double getK1() {
        return 0;
    }

    @Override
    public double getK2() {
        return 0;
    }

    @Override
    public int getRadio() {
        return 0;
    }

    @Override
    public int getPopulationSize() {
        return 0;
    }

    @Override
    public int getParticleSize() {
        return 0;
    }

    @Override
    public void generateParticleMatrix() {

    }

    @Override
    public double getAlpha() {
        return 0;
    }

    @Override
    public void createPopulation() {

    }
}
