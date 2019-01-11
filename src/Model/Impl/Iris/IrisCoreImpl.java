package Model.Impl.Iris;

import Model.Abstraction.Colony;
import Model.Abstraction.Core;
import Model.Abstraction.Function;
import Model.Abstraction.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

public class IrisCoreImpl implements Core<double[]> {

    private IrisColony population;
    private final double k1;
    private final double k2;
    private final int sizeX;
    private final int sizeY;
    private final int r;
    private final int populationSize;
    private final int particleSize;
    private final double alpha;
    private Random rand = new Random();
    private Item<double[]>[][] grid;
    private Function functions = new IrisFunction();


    public IrisCoreImpl(int population, int particles, double k1, double k2, int sizeX, int sizeY, int r, double alpha) {
        this.k1 = k1;
        this.k2 = k2;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.r = r;
        this.populationSize = population;
        this.particleSize = particles;
        this.alpha = alpha;
        this.population = null;
    }

    @Override
    public int getMaxX() {
        return sizeX;
    }

    @Override
    public int getMaxY() {
        return sizeY;
    }

    @Override
    public Colony<double[]> getPopulation() {
        return population;
    }

    @Override
    public Item<double[]>[][] getParticles() {
        return grid;
    }

    @Override
    public double getK1() {
        return k1;
    }

    @Override
    public double getK2() {
        return k2;
    }

    @Override
    public int getRadio() {
        return r;
    }

    @Override
    public int getPopulationSize() {
        return populationSize;
    }

    @Override
    public int getParticleSize() {
        return particleSize;
    }

    @Override
    public void generateParticleMatrix() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/data/iris.csv"));
            int particleSizeCopy = particleSize;
            grid = new IrisItem[sizeX][sizeY];
            while (br.readLine()!=null){
                StringTokenizer st = new StringTokenizer(br.readLine(),",");
                int x=rand.nextInt(sizeX);
                int y=rand.nextInt(sizeY);
                if(grid[x][y]==null)
                    grid[x][y]=new IrisItem(Double.parseDouble(st.nextToken()),
                            Double.parseDouble(st.nextToken()),
                            Double.parseDouble(st.nextToken()),
                            Double.parseDouble(st.nextToken()),
                            st.nextToken());
            }
        } catch (IOException e) {
            throw new NullPointerException("No se encuentran los datos");
        }

    }

    @Override
    public double getAlpha() {
        return alpha;
    }

    @Override
    public void createPopulation() {
        try {
            population = new IrisColony(populationSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void iterate() {
        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                IrisAnt tempAgent = (IrisAnt) this.getPopulation().getAnts()[i][j];
                IrisItem tempParticle = (IrisItem) this.getParticles()[i][j];
                if (tempAgent != null) {
                    if (!tempAgent.hasPayload() && tempParticle != null) {
                        int pp = functions.probPick(i, j, tempParticle);
                        if (rand.nextInt(100) + 1 < pp) {
                            tempAgent.setItem(tempParticle);
                            this.getParticles()[i][j] = null;
                        }
                    } else if (tempAgent.hasPayload() && tempParticle == null) {
                        int pd = functions.probDeposit(i, j, tempAgent.getItem());
                        // System.out.println(pd);
                        if (rand.nextInt(100) + 1 < pd) {
                            this.getParticles()[i][j] = tempAgent.getItem();
                            tempAgent.setItem(null);
                        }
                    }
                }
                this.getPopulation().move(i, j);
            }
        }
    }
}
