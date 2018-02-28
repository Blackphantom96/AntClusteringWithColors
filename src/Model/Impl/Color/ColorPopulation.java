package Model.Impl.Color;

import java.util.Random;

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
		while(numberOfAgents!=0) {
			int x=rand.nextInt(maxX);
			int y=rand.nextInt(maxY);
			if(agents[x][y]==null) {
				agents[x][y]=new ColorAgent(x, y);
				numberOfAgents--;
			}
		}
	}

	@Override
	public void update() {
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) { //FIXME esto puede ocasionar un doble movimiento en los agentes. se propone poner un booleano a ver si ya fue movido. 
				if(agents[i][j]!=null) {
					int[] moved = agents[i][j].move();
					while(agents[moved[0]][moved[1]]!=null) //para que no se "sobre escriban" agentes.
						moved = agents[i][j].move();
					ColorAgent temp = agents[i][j]; // no se si funcione :v ; si no funciona tocaria clonar el agente.
					agents[i][j]=null;
					agents[moved[0]][moved[1]] = temp;
					agents[moved[0]][moved[1]].setPosX(moved[0]); //y se le vuelven a asignar las coordenadas al agente.
					agents[moved[0]][moved[1]].setPosY(moved[1]);
				}
			}
		}
	}

	@Override
	public Agent[][] getAgents() {
		return agents;
	}

}
