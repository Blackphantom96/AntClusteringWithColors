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

import Model.Abstraction.Ant;
import Model.Abstraction.Core;
import Model.Abstraction.Function;
import Model.Abstraction.Item;
import Model.Impl.CoreFactoryCreator;
import Model.Impl.Iris.IrisAnt;
import Model.Impl.Iris.IrisFunction;
import Model.Impl.Iris.IrisItem;
import utiles.LAB;

public class Drawer extends JPanel implements KeyListener, ActionListener {
	private Timer time = new Timer(1, this);
	private static final Color SETOSA = Color.BLUE;
	private static final Color VERSICOLOR = Color.GREEN;
	private static final Color VIRGINICA = Color.RED;
	private boolean flagRealColor = true;
	// public static Objetive ob = new Objetive();
	public static int mutation;
	public static int pobla;
	public int count = 0;
	// public Colony pop;
	private Core core;
	private Function functions = new IrisFunction();
	private boolean flag;
	private double scale;
	private int cicles;
	private int maxX;
	private int maxY;
	private Random rand;

	public Drawer(int population, int particles, double k1, double k2, int gridX, int gridY, int radio, double alpha,
			double scale, int cicles) {
		core = CoreFactoryCreator.getFactory().createInstance(population, particles, k1, k2, gridX,
				gridY, radio, alpha);
		core.createPopulation();
		core.generateParticleMatrix();
		flag = true;
		this.scale = scale;
		this.cicles = cicles;
		maxX = core.getMaxX();
		maxY = core.getMaxY();
		rand = new Random();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time.start();
	}

	public void paintComponent(Graphics g) {
		Graphics2D h = (Graphics2D) g;
		h.scale(scale, scale);
		setBackground(Color.white);
		super.paintComponent(g);
		Item[][] temp = core.getParticles();
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxX; j++) {
				Item<?> q = temp[i][j];
				if (q != null) {
					double[] prop = (double[]) q.getProperties();
					Color res = Color.BLACK;
					if(flagRealColor){
						LAB lab = new LAB(50,(prop[3]*256.0/2.5)-128,(prop[2]*256.0/7.0)-128);
						res = lab.toRGB();
					}else{
						switch (q.toString()){
							case "Iris-setosa":
								res = SETOSA;
								break;
							case "Iris-versicolor":
								res = VERSICOLOR;
								break;
							case "Iris-virginica":
								res = VIRGINICA;
								break;
						}
					}
					g.setColor(res);
					g.drawOval(i, j, core.getRadio(), core.getRadio());
				}
			}
		}
		g.setColor(Color.BLACK);
		Ant[][] temp1 = core.getPopulation().getAnts();
		for (Ant[] p : temp1) {
			for (Ant q : p) {
				if (q != null) {
					if (q.hasPayload()) {
						if(flagRealColor) {
							double[] prop = (double[]) q.getItem().getProperties();
							LAB lab = new LAB(50.0, prop[0] * prop[1], prop[2] * prop[3]);
							g.setColor(lab.toRGB());
						}
					} else {
						g.setColor(Color.BLACK);
					}
					// g.drawRect(q.getPosX(), q.getPosY(), 2, 2);
					g.fillRect(q.getPosX(), q.getPosY(), 3, 3);
				}
			}
		}
		// g.drawString("Mutation: " + mutation + "%", 0, 410);
		// g.drawString("Colony: " + pobla, 0, 425);
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
		if(e.getKeyChar()==' '){
			flagRealColor = !flagRealColor;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (cicles != 0)
			repaint();
		// System.out.println(a.getPopulation().toString());
		// for (Item<int[]>[] p : a.getParticles()) {
		// for (Item<int[]> q : p)
		// System.out.print(q != null ? q : ".");
		// System.out.println();
		// }

		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				IrisAnt tempAgent = (IrisAnt) core.getPopulation().getAnts()[i][j];
				IrisItem tempParticle = (IrisItem) core.getParticles()[i][j];
				if (tempAgent != null) {
					if (!tempAgent.hasPayload() && tempParticle != null) {
						int pp = functions.probPick(i, j, tempParticle);
						if (rand.nextInt(100) + 1 < pp) {
							tempAgent.setItem(tempParticle);
							core.getParticles()[i][j] = null;
						}
					} else if (tempAgent.hasPayload() && tempParticle == null) {
						int pd = functions.probDeposit(i, j, tempAgent.getItem());
						// System.out.println(pd);
						if (rand.nextInt(100) + 1 < pd) {
							core.getParticles()[i][j] = tempAgent.getItem();
							tempAgent.setItem(null);
						}
					}
				}
				core.getPopulation().move(i, j);
			}
		}
		cicles--;
	}

}
