package Model.Impl;

import java.util.*;

import Model.Abstraction.Agent;
import Model.Abstraction.Core;
import Model.Abstraction.Particle;
import Model.Impl.Color.*;

public class ConsoleMain {
	public static void printHelp() {
		System.out.println("Input Data:\n" + "population: int\n" + "particles: int\n" + "k1: double\n" + "k2: double\n"
				+ "sizeX: int\n" + "sizeY: int\n" + "radio: int\n" + "aplha: double\n"
				+ "iterations: int (-1 for infinite)\n" + "printStatus: boolean (true | false) <optional>");
	}

	private static Map<String, Number> inputData;
	private static boolean shouldPrintStatus;
	private static ColorCoreImpl a;

	private static final int MIN_NUMBER_ARGS = 9;

	public static void parseInputData(String[] args) throws Exception {
		inputData = new HashMap<>();

		System.out.println("Input Data: " + Arrays.toString(args));
		System.out.println("Size of Input Data: " + args.length);

		if (args.length < MIN_NUMBER_ARGS) {
			System.err.println("Error: must be at least " + MIN_NUMBER_ARGS + " parameters");
			printHelp();
			System.exit(1);
		}

		inputData.put("population", Integer.parseInt(args[0]));
		inputData.put("particles", Integer.parseInt(args[1]));
		inputData.put("k1", Double.parseDouble(args[2]));
		inputData.put("k2", Double.parseDouble(args[3]));
		inputData.put("sizeX", Integer.parseInt(args[4]));
		inputData.put("sizeY", Integer.parseInt(args[5]));
		inputData.put("radio", Integer.parseInt(args[6]));
		inputData.put("alpha", Double.parseDouble(args[7]));
		inputData.put("iterations", Integer.parseInt(args[8]));

		if (args.length == MIN_NUMBER_ARGS + 1) {
			shouldPrintStatus = Boolean.parseBoolean(args[9]);
		} else {
			shouldPrintStatus = true;
		}

		System.out.println(inputData);
	}

	public static void main(String[] args) throws Exception {
		Random rand = new Random();
		parseInputData(args);

		a = (ColorCoreImpl) CoreFactoryCreator.getFactory().createInstance(inputData.get("population").intValue(),
				inputData.get("particles").intValue(), inputData.get("k1").doubleValue(),
				inputData.get("k2").doubleValue(), inputData.get("sizeX").intValue(), inputData.get("sizeY").intValue(),
				inputData.get("radio").intValue(), inputData.get("alpha").doubleValue());
		ColorFunction functions = new ColorFunction();

		a.createPopulation();
		a.generateParticleMatrix();
		int maxX = a.getMaxX();
		int maxY = a.getMaxY();
		int maxIterations = inputData.get("iterations").intValue();
		int iteration = 0;

		if (shouldPrintStatus) {
			printMatrixStatus(a);
		}

		printMetrics(a);

		while (iteration++ < maxIterations || maxIterations == -1) {
			if (iteration % 100 == 0 && shouldPrintStatus) {
				System.out.println("Iteration: " + iteration);
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

		if (shouldPrintStatus) {
			printMatrixStatus(a);
		}

		printMetrics(a);
	}

	public static void printMatrixStatus(Core<int[]> core) {
		System.out.println(core.getPopulation().toString());
		for (Particle<int[]>[] p : core.getParticles()) {
			for (Particle<int[]> q : p)
				System.out.print(q != null ? q : ".");
			System.out.println();
		}
	}

	public static void printMetrics(Core<int[]> core) { // este metodo NO se debe llamar en medio de la ejecucion
		List<Double> metricsData = new ArrayList<>();

		for (Agent[] agents : core.getPopulation().getAgents()) {
			for (Agent agent : agents) {
				if (agent != null) {
					int i = agent.getPosX();
					int j = agent.getPosY();
					Particle<int[]>[][] particles = core.getParticles();
					if (particles[i][j] != null) {
						// System.err.println("Oops " + i + " " + j);
						particles[i][j] = ((Particle<int[]>) agent.getParticle());
					}
					agent.setParticle(null);
				}
			}
		}

		for (int i = 0; i < core.getMaxX(); i++) {
			for (int j = 0; j < core.getMaxY(); j++)
				if (core.getParticles()[i][j] != null) {
					Particle<int[]> par = core.getParticles()[i][j];
					double metric = calculateMetric(par, i, j);
					metricsData.add(metric);
				}
		}
		printMetricFormat(metricsData);
	}

	public static void printMetricFormat(List<Double> values) {
		int i = 1;
		System.out.println("Particle Metric");
		for (Double val : values) {
			System.out.println(i++ + " " + val);
		}
	}

	public static double calculateMetric(Particle<int[]> p, int i1, int j1) {
		double sum = 0.0;
		for (int i = 0; i < a.getMaxX(); i++) {
			for (int j = 0; j < a.getMaxY(); j++)
				if (j1 != j && i1 != i && a.getParticles()[i][j] != null) {
					Particle<int[]> par = a.getParticles()[i][j];
					double euclidenanDistance = Math.sqrt((j1 - j) * (j1 - j) + (i1 - i) * (i1 - i));
					sum += (1.0 / par.euclideanDistance(p)) * (1.0 / euclidenanDistance);
				}
		}
		return sum;
	}
}
