package Model.Impl.Iris.Factory;

import Model.Abstraction.Factory.CoreFactory;
import Model.Impl.Iris.IrisCoreImpl;

public class IrisCoreImplFactory implements CoreFactory {

    private IrisCoreImpl irisInstance;

    @Override
    public IrisCoreImpl getInstance() {
        if (irisInstance == null) {
            throw new NullPointerException("Instancia de Core no fue creada.");
        }
        return irisInstance;
    }

    @Override
    public IrisCoreImpl createInstance(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r, double alpha) {
        irisInstance  = new IrisCoreImpl(population, particles, k1, k2, sizeX, sizeY, r, alpha);
        return irisInstance;
    }
}
