package Model.Impl.Iris;

import Model.Abstraction.Item;
import Model.Impl.CoreFactoryCreator;
import utiles.LAB;

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
        double res = 0.0;
        for(int i = 0;i < properties.length;i++){
            res+=(properties[i]-p.getProperties()[i])*(properties[i]-p.getProperties()[i]);
        }
        double[] prop = getProperties();
        double[] prop2 = getProperties();

        LAB lab1 = new LAB(50,(prop[3]*256.0/2.5)-128,(prop[2]*256.0/7.0)-128);
        LAB lab2 = new LAB(50,(prop2[3]*256.0/2.5)-128,(prop2[2]*256.0/7.0)-128);
        return LAB.ciede2000(lab1,lab2);
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
