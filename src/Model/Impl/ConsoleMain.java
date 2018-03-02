package Model.Impl;

import java.util.*;

import Model.Abstraction.Particle;
import Model.Impl.Color.*;

public class ConsoleMain {
	public static void main(String[] args) {
		Random rand = new Random();
		ColorCoreImpl a = (ColorCoreImpl) CoreFactoryCreator.getFactory().createInstance(100, 300, 0.5, 0.9, 40, 100, 1,
				442.0);
		ColorFunction functions = new ColorFunction();
		a.createPopulation();
		a.generateParticleMatrix();
		int maxX = a.getMaxX();
		int maxY = a.getMaxY();
		int iteration = 0;
		while (iteration++ < 10000) {
			System.out.println(a.getPopulation().toString());
			for (Particle<int[]>[] p : a.getParticles()) {
				for (Particle<int[]> q : p)
					System.out.print(q != null ? q : ".");
				System.out.println();
			}
			
			for (int i = 0; i < maxX; i++) {
				for (int j = 0; j < maxY; j++) {
					ColorAgent tempAgent = (ColorAgent) a.getPopulation().getAgents()[i][j];
					ColorParticle tempParticle = (ColorParticle) a.getParticles()[i][j];
					if (tempAgent != null) {
						if (!tempAgent.hasPayload() && tempParticle != null) {
							int pp = functions.probPick(i, j, tempParticle);
							if (rand.nextInt(100) + 1 < pp) {
								tempAgent.setParticle(tempParticle);
								a.getParticles()[i][j] = null;
							}
						} else if (tempAgent.hasPayload() && tempParticle == null) {
							int pd = functions.probDeposit(i, j, tempAgent.getParticle());
							// System.out.println(pd);
							if (rand.nextInt(100) + 1 < pd) {
								a.getParticles()[i][j] = tempAgent.getParticle();
								tempAgent.setParticle(null);
							}
						}
					}
					a.getPopulation().move(i, j);
				}
			}
		}
	}
}
