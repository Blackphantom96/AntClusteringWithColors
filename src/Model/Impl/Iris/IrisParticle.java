package Model.Impl.Iris;

import Model.Abstraction.Particle;
import Model.Impl.CoreFactoryCreator;

import java.util.Random;

public class IrisParticle implements Particle<double[]> { // TODO: anzola

    private static final double MAX_DEPTH = 10.0; // TODO: revisar

    private int posX, posY;
    private static Random rand = new Random();
    private double[] properties;


    public IrisParticle() {
        this(
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH); // TODO revisar
    }

    public IrisParticle(double a, double b, double c, double d) {
        int maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
        int maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
        posX = rand.nextInt(maxX);
        posY = rand.nextInt(maxY);
        properties = new double[]{a, b, c, d};
    }


    @Override
    public double[] getProperties() {
        return this.properties;
    }

    @Override
    public double distance(Particle<double[]> p) {
        return 0.0; // TODO poner con CIELAB
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }
}
