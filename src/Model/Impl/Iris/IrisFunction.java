package Model.Impl.Iris;

import Model.Abstraction.Function;
import Model.Abstraction.Item;
import Model.Impl.CoreFactoryCreator;

public class IrisFunction implements Function<double[]> {

    @Override
    public int probPick(int x, int y, Item<double[]> p) {
        double k1 = CoreFactoryCreator.getFactory().getInstance().getK1();
        return (int) (Math.pow(
                k1 / (k1 + itemAverageSimilarity(x, y, CoreFactoryCreator.getFactory().getInstance().getRadio(), p)), 2)
                * 100.0);
    }

    @Override
    public int probDeposit(int x, int y, Item<double[]> p) {
        double fx = itemAverageSimilarity(y, x, CoreFactoryCreator.getFactory().getInstance().getRadio(), p);
        double k2 = CoreFactoryCreator.getFactory().getInstance().getK2();
        return (int) ((fx < k2 ? 2*fx : 1.0) * 100.0);
    }

    @Override
    public double itemPerceivedFraction(int x, int y, int r, Item<double[]> p) {
        System.out.println("Esta funcion no debe llamarse");
        return 0.0;
    }

    @Override
    public double itemAverageSimilarity(int x, int y, int r, Item<double[]> p) {
        int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
        int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
        double alpha = CoreFactoryCreator.getFactory().getInstance().getAlpha();
        double sum = 0.0;
        for (int i = x - r; i <= x + r; i++) {
            for (int j = y - r; j <= y + r; j++) {
                if (0 <= i && i < maxX && 0 <= j && j < maxY && x!=i && y!=j) {
                    IrisItem tempParticle = (IrisItem) CoreFactoryCreator.getFactory().getInstance()
                            .getParticles()[i][j];
                    Item[][] xxxx = CoreFactoryCreator.getFactory().getInstance()
                            .getParticles();
                    if (tempParticle != null) {
                        double distances = p.distance(tempParticle) ;
                        sum += 1.0 - (distances/ alpha);
                    }
                }
            }
        }
        double f = sum / (r * r);
        return f > 0.0 ? f : 0.0;
    }
}
