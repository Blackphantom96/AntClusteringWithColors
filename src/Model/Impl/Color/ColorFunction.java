package Model.Impl.Color;

import java.util.List;

import Model.Abstraction.Function;
import Model.Abstraction.Particle;
import Model.Impl.CoreFactoryCreator;

public class ColorFunction implements Function<List<Integer>> {

	@Override
	public int probPick(int x, int y, Particle<List<Integer>> p) { // Eq 5.14
		double k1 = CoreFactoryCreator.getFactory().getInstance().getK1();
		return (int) Math.pow(k1 / (k1 + itemAverageSimilarity(x, y, CoreFactoryCreator.getFactory().getInstance().getRadio(), p)), 2) * 100;
	}

	@Override
	public int probDeposit(int x, int y, Particle<List<Integer>> p) { // Eq 5.15
		double fx = itemAverageSimilarity(x, y, CoreFactoryCreator.getFactory().getInstance().getRadio(), p);
		double k2 = CoreFactoryCreator.getFactory().getInstance().getK2();
		return (int) (fx < k2 ? 2.0 * fx + 0.5 : 1.0);
	}

	@Override
	public double itemPerceivedFraction(int x, int y, int r, Particle<List<Integer>> p) {
		// TODO por definir
		return 0;
	}

	@Override
	public double itemAverageSimilarity(int x, int y, int r, Particle<List<Integer>> p) { // Eq 5.13
		double f = itemAverageSimilarity(x, y, r, p);
		
		// TODO por definir
		
		return 0.0;
	}

}
