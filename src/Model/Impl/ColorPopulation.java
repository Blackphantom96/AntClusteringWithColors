package Model.Impl;

import Model.Abstraction.*;

public class ColorPopulation implements Population{

	private ColorAgent [][] agents;
	public ColorPopulation() {
		agents = new ColorAgent[Core.getMaxX()][Core.getMaxY()];
		for(int i =0 ; i< Core.getMaxX();i++) {
			for(int j=0; i< Core.getMaxY();j++){
				agents[i][j]= new ColorAgent();
			}
		}
	}
	
	@Override
	public void update() {
		for(int i =0 ; i< Core.getMaxX();i++) {
			for(int j=0; i< Core.getMaxY();j++){
				agents[i][j].iterate();
			}
		}
	}

	@Override
	public Agent[][] getAgents() {
		return agents;
	}

}
