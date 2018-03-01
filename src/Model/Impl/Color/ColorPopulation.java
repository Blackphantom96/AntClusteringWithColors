package Model.Impl.Color;

import java.util.*;

import Model.Abstraction.*;
import Model.Impl.CoreFactoryCreator;

public class ColorPopulation implements Population {

	private Random rand = new Random();

	private ColorAgent[][] agents;
	private int maxX;
	private int maxY;

	public ColorPopulation(int population) {
		this.maxX = CoreFactoryCreator.getFactory().getInstance().getMaxX();
		this.maxY = CoreFactoryCreator.getFactory().getInstance().getMaxY();
		agents = new ColorAgent[maxX][maxY];
		int numberOfAgents = CoreFactoryCreator.getFactory().getInstance().getPopulationSize();
		while (numberOfAgents != 0) {
			int x = rand.nextInt(maxX);
			int y = rand.nextInt(maxY);
			if (agents[x][y] == null) {
				agents[x][y] = new ColorAgent(x, y);
				numberOfAgents--;
			}
		}
	}

	@Override
	public void move(int i, int j) {
		if (agents[i][j] != null) {
			int[] moved = agents[i][j].move();
			while (agents[moved[0]][moved[1]] != null && !(moved[0]==i && moved[1]==j) ) 
				moved = agents[i][j].move();
			ColorAgent temp = agents[i][j]; 
			agents[i][j] = null;
			agents[moved[0]][moved[1]] = temp;
			agents[moved[0]][moved[1]].setPosX(moved[0]);
			agents[moved[0]][moved[1]].setPosY(moved[1]);
		}
	}

	@Override
	public Agent[][] getAgents() {
		return agents;
	}

	@Override
	public String toString() {
		String res = "\n";
		for (Agent[] p : agents) {
			for(Agent q : p)
				res += q!=null? q.hasPayload()?"x":"*":".";
			res+="\n";
		}
		return res;
	}

}
