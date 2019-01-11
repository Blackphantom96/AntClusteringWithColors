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

import Model.Abstraction.Core;
import Model.Abstraction.Function;
import Model.Impl.CoreFactoryCreator;
import Model.Impl.Iris.IrisAnt;
import Model.Impl.Iris.IrisFunction;
import Model.Impl.Iris.IrisItem;
import View.Entities.Iris.VisualIrisColony;
import View.Entities.Iris.VisualIrisItems;

public class Drawer extends JPanel implements KeyListener, ActionListener {

	private int maxX;
	private int maxY;
	private int cicles;
	private double scale;
	private Core core;
	private VisualIrisColony colony;
	private VisualIrisItems environment;

	private Timer time = new Timer(1, this);
	private boolean flagRealColor = true;

	private Function functions = new IrisFunction();
	private Random rand;

	//TODO REMOVE THIS!!!!!!!!!
	private static final Color SETOSA = Color.BLUE;
	private static final Color VERSICOLOR = Color.GREEN;
	private static final Color VIRGINICA = Color.RED;


	public Drawer(int population, int particles, double k1, double k2, int gridX, int gridY, int radio, double alpha,
			double scale, int cicles) {
		core = CoreFactoryCreator.getFactory().createInstance(population, particles, k1, k2, gridX,
				gridY, radio, alpha);
		core.createPopulation();
		core.generateParticleMatrix();

		colony = new VisualIrisColony(core.getPopulation());
		environment = new VisualIrisItems((IrisItem[][]) core.getParticles());

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
		for (int i = 0; i < maxX; i++) {
			for (int j = 0; j < maxY; j++) {
				if(environment.getElement(i,j)!=null) {
					environment.getElement(i,j).draw(i, j, core.getRadio(), flagRealColor, g);
				}
				if(colony.getElement(i,j)!=null){
					colony.getElement(i,j).draw(i, j, core.getRadio(), flagRealColor, g);
				}
			}
		}
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
		colony = new VisualIrisColony(core.getPopulation());
		environment = new VisualIrisItems((IrisItem[][]) core.getParticles());

		if (cicles != 0)
			repaint();

		//TODO pasar esto al core!
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
