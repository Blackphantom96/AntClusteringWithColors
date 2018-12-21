package Model.Impl;

import Model.Abstraction.Factory.CoreFactory;
import Model.Impl.Color.Factory.ColorCoreImplFactory;
import Model.Impl.Iris.Factory.IrisCoreImplFactory;

public class CoreFactoryCreator {

	private static CoreFactory factoryInstance;

	public static CoreFactory getFactory() {
		if (factoryInstance == null) {
			factoryInstance = new IrisCoreImplFactory();
		}
		return factoryInstance;
	}

}
