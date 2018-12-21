package Model.Impl.Iris;

import Model.Abstraction.Particle;

public class IrisParticle implements Particle<int[]> {
    @Override
    public int[] getProperties() {
        return new int[0];
    }

    @Override
    public double distance(Particle<int[]> p) {
        return 0;
    }

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 0;
    }
}
