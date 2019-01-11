package Model.Impl.Iris;

import Model.Abstraction.Item;
import Model.Impl.CoreFactoryCreator;
import Model.Impl.CustomDistanceFunctions.CielabDistanceFunction;
import de.lmu.ifi.dbs.elki.data.DoubleVector;
import de.lmu.ifi.dbs.elki.distance.distancefunction.*;
import utiles.DistanceFunctionCalculator;

import java.util.Arrays;
import java.util.Random;

public class IrisItem implements Item<double[]> {

    private static final double MAX_DEPTH = 10.0;

    private int posX, posY;
    private static Random rand = new Random();
    private double[] properties;
    private String tipo;

    public IrisItem() {
        this(   rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                "Random");
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
        double[] prop = getProperties();
        double[] prop2 = p.getProperties();

        try {
            return DistanceFunctionCalculator.calculateDistance(CanberraDistanceFunction.class, new DoubleVector.Factory(), prop, prop2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //TODO: ver funciones https://elki-project.github.io/algorithms/distances de la familia minowski.
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
    public String getRealClass() {
        return tipo;
    }

    @Override
    public String toString() {
        return "IrisItem{" +
                "posX=" + posX +
                ", posY=" + posY +
                ", properties=" + Arrays.toString(properties) +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
