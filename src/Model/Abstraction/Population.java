package Model.Abstraction;

import Model.Impl.CoreFactoryCreator;

import java.lang.reflect.Constructor;
import java.util.Random;

public abstract class Population<E> {

    private Random rand = new Random();

    private Agent[][] agents;
    private int maxX;
    private int maxY;

    public Population(Class c, int population) throws Exception {
        this.maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
        this.maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
        agents = new Agent[maxX][maxY];
        int numberOfAgents = CoreFactoryCreator.getFactory().getInstance().getPopulationSize();
        while (numberOfAgents != 0) {
            int x = rand.nextInt(maxX);
            int y = rand.nextInt(maxY);
            if (agents[x][y] == null) {
                agents[x][y] = instantiateAgent(c, x, y);
                numberOfAgents--;
            }
        }
    }

    private Agent instantiateAgent(Class c, int x, int y) throws Exception {
        Constructor<?> ctor = c.getConstructor();
        Agent object = (Agent) ctor.newInstance();
        object.setPosX(x);
        object.setPosY(y);
        return object;
    }

    public void move(int i, int j) {
        if (agents[i][j] != null) {
            int[] moved = agents[i][j].move();
            while (agents[moved[0]][moved[1]] != null && !(moved[0] == i && moved[1] == j)) {
                moved = agents[i][j].move();
            }
            Agent temp = agents[i][j];
            agents[i][j] = null;
            agents[moved[0]][moved[1]] = temp;
            agents[moved[0]][moved[1]].setPosX(moved[0]);
            agents[moved[0]][moved[1]].setPosY(moved[1]);
        }
    }

    public Agent<E>[][] getAgents() {
        return agents;
    }

    public String toString() {
        String res = "\n";
        for (Agent[] p : this.getAgents()) {
            for (Agent q : p)
                res += q != null ? q.hasPayload() ? "x" : "*" : ".";
            res += "\n";
        }
        return res;
    }


}
