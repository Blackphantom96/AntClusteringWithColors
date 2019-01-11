package View;

import Model.Abstraction.Core;
import Model.Impl.CoreFactoryCreator;
import Model.Impl.Iris.IrisItem;
import View.Entities.Iris.VisualIrisColony;
import View.Entities.Iris.VisualIrisItems;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Drawer extends JPanel implements KeyListener, ActionListener {

	private int maxX;
	private int maxY;
	private int cicles;
	private double scale;
	private Core core;
	private VisualIrisColony colony;
	private VisualIrisItems environment;

	private Timer time = new Timer(0, this);
	private boolean flagRealColor = true;

	private Random rand;

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

	}

	@Override
	public void keyPressed(KeyEvent e) {

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

		core.iterate();

		cicles--;
	}
}
