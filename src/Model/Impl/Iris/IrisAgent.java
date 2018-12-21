package Model.Impl.Iris;

import Model.Abstraction.Agent;
import Model.Abstraction.Particle;

public class IrisAgent implements Agent<int[]> {
    @Override
    public void iterate() {

    }

    @Override
    public int[] move() {
        return new int[0];
    }

    @Override
    public int getPosX() {
        return 0;
    }

    @Override
    public int getPosY() {
        return 0;
    }

    @Override
    public Particle<int[]> getParticle() {
        return null;
    }

    @Override
    public boolean hasPayload() {
        return false;
    }

    @Override
    public void setPosX(int x) {

    }

    @Override
    public void setPosY(int y) {

    }

    @Override
    public void setParticle(Particle<int[]> tempParticle) {

    }
}
