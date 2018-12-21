package Model.Impl.Iris;

import Model.Abstraction.Particle;
import Model.Impl.CoreFactoryCreator;
import utiles.LAB;

import java.util.Random;

public class IrisParticle implements Particle<double[]> { // TODO: anzola

    private static final double MAX_DEPTH = 10.0; // TODO: revisar

    private int posX, posY;
    private static Random rand = new Random();
    private double[] properties;
    private String tipo;


    public IrisParticle() {
        this(   rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                rand.nextDouble() * MAX_DEPTH,
                "Random"); // TODO revisar
    }

    public IrisParticle(double a, double b, double c, double d,String tipo) {
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
    public double distance(Particle<double[]> p) {
        double res = 0.0;
        for(int i = 0;i < properties.length;i++){
            res+=(properties[i]-p.getProperties()[i])*(properties[i]-p.getProperties()[i]);
        }
        LAB lab1 = new LAB(getProperties()[3]*100.0/2.5,(getProperties()[0]*getProperties()[1]*256.0/30.0)-128,
                (getProperties()[2]*getProperties()[3]*256.0/6.0)-128);
        LAB lab2 = new LAB(getProperties()[3]*100.0/2.5,(p.getProperties()[0]*p.getProperties()[1]*256.0/30.0)-128,
                (p.getProperties()[2]*p.getProperties()[3]*256.0/6.0)-128);
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
