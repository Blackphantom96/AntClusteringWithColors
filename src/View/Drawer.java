package View;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Abstraction.Agent;
import Model.Abstraction.Particle;
import Model.Impl.CoreFactoryCreator;
import Model.Impl.Color.ColorAgent;
import Model.Impl.Color.ColorCoreImpl;
import Model.Impl.Color.ColorFunction;
import Model.Impl.Color.ColorParticle;

public class Drawer extends JPanel implements KeyListener, ActionListener {
	private Timer time = new Timer(1, this);

	// public static Objetive ob = new Objetive();
	public static int mutation;
	public static int pobla;
	public int count = 0;
	// public Population pop;
	private ColorCoreImpl core;
	private ColorFunction functions = new ColorFunction();
	private boolean flag;
	private double scale;
	private int cicles;
	private int maxX;
	private int maxY;
	private Random rand;

	public Drawer(int population, int particles, double k1, double k2, int gridX, int gridY, int radio, double alpha,
			double scale, int cicles) {
		core = (ColorCoreImpl) CoreFactoryCreator.getFactory().createInstance(population, particles, k1, k2, gridX,
				gridY, radio, alpha);
		core.createPopulation();
		core.generateParticleMatrix();
		flag = true;
		this.scale = scale;
		this.cicles = cicles;
		maxX = core.getMaxX();
		maxY = core.getMaxY();
		rand = new Random();
		time.start();
	}

	public void paintComponent(Graphics g) {
		Graphics2D h = (Graphics2D) g;
		h.scale(scale, scale);
		setBackground(Color.white);
		super.paintComponent(g);
		Particle[][] temp = core.getParticles();
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxX; j++) {
				Particle<?> q = temp[i][j];
				if (q != null) {
					int[] prop = (int[]) q.getProperties();
					g.setColor(new Color(prop[0], prop[1], prop[2]));
					g.drawOval(i, j, core.getRadio(), core.getRadio());
				}
			}
		}
		g.setColor(Color.BLACK);
		Agent[][] temp1 = core.getPopulation().getAgents();
		for (Agent[] p : temp1) {
			for (Agent q : p) {
				if (q != null) {
					if (q.hasPayload()) {
						int[] prop = (int[]) q.getParticle().getProperties();
						g.setColor(new Color(prop[0], prop[1], prop[2], 100));
					} else {
						g.setColor(Color.BLACK);
					}
					// g.drawRect(q.getPosX(), q.getPosY(), 2, 2);
					g.fillRect(q.getPosX(), q.getPosY(), 3, 3);
				}
			}
		}
		// g.drawString("Mutation: " + mutation + "%", 0, 410);
		// g.drawString("Population: " + pobla, 0, 425);
		// g.drawString("Generation: " + pop.generation, 0, 440);
		// g.drawString("Cicles: " + (cicles - count), 0, 455);
		// g.drawString("Max fitness: " + pop.maxFitness, 0, 470);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (cicles != 0)
			repaint();
		// System.out.println(a.getPopulation().toString());
		// for (Particle<int[]>[] p : a.getParticles()) {
		// for (Particle<int[]> q : p)
		// System.out.print(q != null ? q : ".");
		// System.out.println();
		// }

		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				ColorAgent tempAgent = (ColorAgent) core.getPopulation().getAgents()[i][j];
				ColorParticle tempParticle = (ColorParticle) core.getParticles()[i][j];
				if (tempAgent != null) {
					if (!tempAgent.hasPayload() && tempParticle != null) {
						int pp = functions.probPick(i, j, tempParticle);
						if (rand.nextInt(100) + 1 < pp) {
							tempAgent.setParticle(tempParticle);
							core.getParticles()[i][j] = null;
						}
					} else if (tempAgent.hasPayload() && tempParticle == null) {
						int pd = functions.probDeposit(i, j, tempAgent.getParticle());
						// System.out.println(pd);
						if (rand.nextInt(100) + 1 < pd) {
							core.getParticles()[i][j] = tempAgent.getParticle();
							tempAgent.setParticle(null);
						}
					}
				}
				core.getPopulation().move(i, j);
			}
		}
		cicles--;
	}

}
