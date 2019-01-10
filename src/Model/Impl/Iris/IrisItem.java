package Model.Impl.Iris;

import Model.Abstraction.Item;
import Model.Impl.CoreFactoryCreator;
import de.lmu.ifi.dbs.elki.data.DoubleVector;
import de.lmu.ifi.dbs.elki.distance.distancefunction.minkowski.EuclideanDistanceFunction;
import utiles.DistanceFunctionCalculator;

import java.util.Random;

public class IrisItem implements Item<double[]> { // TODO: anzola

    private static final double MAX_DEPTH = 10.0; // TODO: revisar

    private int posX, posY;
    private static Random rand = new Random();
    private double[] properties;
    private String tipo;


    public IrisItem() {
        this(   rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                "Random"); // TODO revisar
    }

    public IrisItem(double a, double b, double c, double d, String tipo) {
        int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
        int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
        posX = rand.nextInt(maxX);
        posY = rand.nextInt(maxY);
        properties = new double[]{a, b, c, d};
        this.tipo = tipo;
    }


    @Override
    public double[] getProperties() {
        return this.properties;
    }

    @Override
    public double distance(Item<double[]> p) {
//        double res = 0.0;
//        for(int i = 0;i < properties.length;i++){
//            res+=(properties[i]-p.getProperties()[i])*(properties[i]-p.getProperties()[i]);
//        }

        double[] prop = getProperties();
        double[] prop2 = p.getProperties();

        try {
            return DistanceFunctionCalculator.calculateDistance(EuclideanDistanceFunction.class, new DoubleVector.Factory(), prop, prop2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Double.MAX_VALUE;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
