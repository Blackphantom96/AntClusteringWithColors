package Model.Impl.Iris;

import Model.Abstraction.Function;
import Model.Abstraction.Particle;

public class IrisFunction implements Function<int[]> {

    @Override
    public int probPick(int x, int y, Particle<int[]> p) {
        return 0;
    }

    @Override
    public int probDeposit(int x, int y, Particle<int[]> p) {
        return 0;
    }

    @Override
    public double itemAverageSimilarity(int x, int y, int r, Particle<int[]> p) {
        return 0;
    }

    @Override
    public double itemPerceivedFraction(int x, int y, int r, Particle<int[]> p) {
        return 0;
    }
}
