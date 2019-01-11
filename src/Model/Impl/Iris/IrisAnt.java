package Model.Impl.Iris;

import Model.Abstraction.Ant;
import Model.Abstraction.Item;
import Model.Impl.CoreFactoryCreator;

import java.util.Random;

public class IrisAnt implements Ant<double[]> {

    private Random rand = new Random();
    private int posX, posY;
    private int maxX, maxY;
    private Item item;

    public IrisAnt(int x, int y) {
        this.maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
        this.maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
        this.posX = x;
        this.posY = y;
    }

    public IrisAnt() {
        this(0,0);
    }

    @Override
    public void iterate() {
        move();
    }

    @Override
    public int[] move() {
        int velX = rand.nextInt(3) - 1;
        int velY = rand.nextInt(3) - 1;
        while ((posX + velX) < 0 || (posX + velX) >= maxX)
            velX = rand.nextInt(3) - 1;
        while ((posY + velY) < 0 || (posY + velY) >= maxY) {
            velY = rand.nextInt(3) - 1;
        }
        return new int[] { posX + velX, posY + velY };
    }

    @Override
    public int getPosX() {
        return this.posX;
    }

    @Override
    public int getPosY() {
        return this.posY;
    }

    @Override
    public Item<double[]> getItem() {
        return item;
    }

    @Override
    public boolean hasPayload() {
        return item != null;
    }

    @Override
    public void setPosX(int x) {
        this.posX = x;
    }

    @Override
    public void setPosY(int y) {
        this.posY = y;
    }

    @Override
    public void setItem(Item<double[]> tempItem) {
        item = tempItem;

    }
}
