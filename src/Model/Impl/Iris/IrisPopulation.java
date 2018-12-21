package Model.Impl.Iris;

import Model.Abstraction.Agent;
import Model.Abstraction.Population;

public class IrisPopulation implements Population<int[]> {
    @Override
    public void move(int i, int j) {

    }

    @Override
    public Agent<int[]>[][] getAgents() {
        return new Agent[0][];
    }
}
