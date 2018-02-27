package Model.Impl;

import Model.Abstraction.*;

public class ColorPopulation implements Population{

	private ColorAgent [][] agents;
	public ColorPopulation() {
		agents = new ColorAgent[Core.getInstance().getMaxX()][Core.getInstance().getMaxY()];
		for(int i =0 ; i< Core.getInstance().getMaxX();i++) {
			for(int j=0; i< Core.getInstance().getMaxY();j++){
				agents[i][j]= new ColorAgent();
			}
		}
	}
	
	@Override
	public void update() {
		for(int i =0 ; i< Core.getInstance().getMaxX();i++) {
			for(int j=0; i< Core.getInstance().getMaxY();j++){
				agents[i][j].iterate();
			}
		}
	}

	@Override
	public Agent[][] getAgents() {
		return agents;
	}

}
