package Model.Impl.Iris;

import Model.Abstraction.Population;

public class IrisPopulation extends Population<int[]> { // TODO: anzola
    public IrisPopulation(int population) throws Exception {
        super(IrisAgent.class, population);
    }
}
