package Model.Impl.Iris;

import Model.Abstraction.Colony;

public class IrisColony extends Colony<double[]> { // TODO: anzola
    public IrisColony(int population) throws Exception {
        super(IrisAnt.class, population);
    }
}
