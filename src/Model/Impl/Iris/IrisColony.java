package Model.Impl.Iris;

import Model.Abstraction.Colony;

public class IrisColony extends Colony<double[]> {
    public IrisColony(int population) throws Exception {
        super(IrisAnt.class, population);
    }
}
