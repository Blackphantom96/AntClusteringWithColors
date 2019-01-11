package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Contenedor extends JFrame{
	private Drawer drawer ;
	public Contenedor(int population, int particles, double k1, double k2, int gridX, int gridY, int radio, double alpha, double resX, int cicles) {
		super();
		setTitle("VisualAnt Cluster Simulation");
		setSize((int)resX*gridX, (int)resX*gridX);
		getContentPane().setBackground(Color.white);
		setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;	
		setLocation(xEsquina, yEsquina);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		drawer = new Drawer(population,particles,k1,k2,gridX,gridX,radio,alpha,resX,cicles);
		add(drawer,BorderLayout.CENTER);
	}
}
