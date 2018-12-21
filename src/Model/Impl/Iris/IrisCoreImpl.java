package Model.Impl.Iris;

import Model.Abstraction.Core;
import Model.Abstraction.Particle;
import Model.Abstraction.Population;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

public class IrisCoreImpl implements Core<double[]> {

    private IrisPopulation population;
    private final double k1;
    private final double k2;
    private final int sizeX;
    private final int sizeY;
    private final int r;
    private final int populationSize;
    private final int particleSize;
    private final double alpha;
    private Random rand = new Random();
    private Particle<double[]>[][] grid;


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
    public Population<double[]> getPopulation() {
        return population;
    }

    @Override
    public Particle<double[]>[][] getParticles() {
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
            grid = new IrisParticle[sizeX][sizeY];
            while (br.readLine()!=null){
                StringTokenizer st = new StringTokenizer(br.readLine(),",");
                int x=rand.nextInt(sizeX);
                int y=rand.nextInt(sizeY);
                if(grid[x][y]==null)
                    grid[x][y]=new IrisParticle(Double.parseDouble(st.nextToken()),
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
            population = new IrisPopulation(populationSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
