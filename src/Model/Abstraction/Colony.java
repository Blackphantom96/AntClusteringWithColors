package Model.Abstraction;

import Model.Impl.CoreFactoryCreator;

import java.lang.reflect.Constructor;
import java.util.Random;

public abstract class Colony<E> {

    private Random rand = new Random();

    private Ant[][] ants;
    private int maxX;
    private int maxY;

    public Colony(Class c, int population) throws Exception {
        this.maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
        this.maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
        ants = new Ant[maxX][maxY];
        int numberOfAgents = CoreFactoryCreator.getFactory().getInstance().getPopulationSize();
        while (numberOfAgents != 0) {
            int x = rand.nextInt(maxX);
            int y = rand.nextInt(maxY);
            if (ants[x][y] == null) {
                ants[x][y] = instantiateAgent(c, x, y);
                numberOfAgents--;
            }
        }
    }

    private Ant instantiateAgent(Class c, int x, int y) throws Exception {
        Constructor<?> ctor = c.getConstructor();
        Ant object = (Ant) ctor.newInstance();
        object.setPosX(x);
        object.setPosY(y);
        return object;
    }

    public void move(int i, int j) {
        if (ants[i][j] != null) {
            int[] moved = ants[i][j].move();
            while (ants[moved[0]][moved[1]] != null && !(moved[0] == i && moved[1] == j)) {
                moved = ants[i][j].move();
            }
            Ant temp = ants[i][j];
            ants[i][j] = null;
            ants[moved[0]][moved[1]] = temp;
            ants[moved[0]][moved[1]].setPosX(moved[0]);
            ants[moved[0]][moved[1]].setPosY(moved[1]);
        }
    }

    public Ant<E>[][] getAnts() {
        return ants;
    }

    public String toString() {
        String res = "\n";
        for (Ant[] p : this.getAnts()) {
            for (Ant q : p)
                res += q != null ? q.hasPayload() ? "x" : "*" : ".";
            res += "\n";
        }
        return res;
    }


}
