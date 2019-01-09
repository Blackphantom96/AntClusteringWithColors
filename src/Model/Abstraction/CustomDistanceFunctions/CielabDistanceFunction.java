package Model.Abstraction.CustomDistanceFunctions;

import de.lmu.ifi.dbs.elki.data.NumberVector;
import de.lmu.ifi.dbs.elki.distance.distancefunction.AbstractNumberVectorDistanceFunction;
import utiles.LAB;

public class CielabDistanceFunction extends AbstractNumberVectorDistanceFunction {
	@Override
	public double distance(NumberVector a, NumberVector b) {
		double[] prop = mapValues(a);
		double[] prop2 = mapValues(b);

		LAB lab1 = new LAB(50,(prop[3]*256.0/2.5)-128,(prop[2]*256.0/7.0)-128);
		LAB lab2 = new LAB(50,(prop2[3]*256.0/2.5)-128,(prop2[2]*256.0/7.0)-128);
		return LAB.ciede2000(lab1,lab2);
	}

	double[] mapValues(NumberVector v) {
		int n = v.getDimensionality();
		double[] r = new double[n];

		for (int i = 0; i < n; i++) {
			r[i] = v.doubleValue(i);
		}

		return r;
	}
}
