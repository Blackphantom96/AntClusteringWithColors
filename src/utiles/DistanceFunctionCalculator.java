package utiles;

import Model.Abstraction.Factory.DistanceFunctionFactory;
import de.lmu.ifi.dbs.elki.data.DoubleVector;
import de.lmu.ifi.dbs.elki.data.NumberVector;
import de.lmu.ifi.dbs.elki.distance.distancefunction.AbstractNumberVectorDistanceFunction;
import de.lmu.ifi.dbs.elki.distance.distancefunction.PrimitiveDistanceFunction;

public class DistanceFunctionCalculator {

	public static double calculateDistance(Class c, NumberVector.Factory factory, double[] a, double[] b) throws Exception {
		AbstractNumberVectorDistanceFunction function = DistanceFunctionFactory.getDistanceFunction(c);

		NumberVector vb = factory.newNumberVector(b);
		NumberVector va = factory.newNumberVector(a);

		return ((PrimitiveDistanceFunction<NumberVector>)function).distance(va, vb);
	}

}
