package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {
	private JPanel options;
	private JTextField population;
	private JTextField particles;
	private JTextField k1;
	private JTextField k2;
	private JTextField gridSize;
	private JTextField gridSizeY;
	private JTextField radio;
	private JTextField alpha;
	private JTextField scala;
	private JTextField cicles;
	private JButton ok;

	public static void main(String[] args) {
		Main a = new Main();
		a.setVisible(true);
	}

	public Main() {
		super();
		setTitle("Ant clustering");
		setSize(400, 400);
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xEsquina = (screen.width - getSize().width) / 2;
		int yEsquina = (screen.height - getSize().height) / 2;
		setLocation(xEsquina, yEsquina);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		prepareCentro();
		add(options, BorderLayout.CENTER);
		prepareAcciones();

	}

	private void prepareAcciones() {
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Contenedor con = new Contenedor(Integer.parseInt(population.getText()),
						Integer.parseInt(particles.getText()), Double.parseDouble(k1.getText()),
						Double.parseDouble(k2.getText()), Integer.parseInt(gridSize.getText()),
						Integer.parseInt("0"), Integer.parseInt(radio.getText()),
						Double.parseDouble(alpha.getText()), Double.parseDouble(scala.getText()),
						Integer.parseInt(cicles.getText()));
				con.setVisible(true);
			}
		});
	}

	private void prepareCentro() {
		options = new JPanel(new BorderLayout());
		JPanel form = new JPanel(new GridLayout(11, 4));
		population = new JTextField("10");
		particles = new JTextField("50");
		k1 = new JTextField("0.5");
		k2 = new JTextField("0.7");
		radio = new JTextField("3");
		alpha = new JTextField("50.0");
		gridSize = new JTextField("100");
		gridSizeY = new JTextField("100");
		scala = new JTextField("3");
		cicles = new JTextField("-1");

		form.add(new JLabel("Particles:"));
		form.add(particles);
		form.add(new JLabel("Population:"));
		form.add(population);
		form.add(new JLabel("k1:"));
		form.add(k1);
		form.add(new JLabel("k2:"));
		form.add(k2);
		form.add(new JLabel("Radio:"));
		form.add(radio);
		form.add(new JLabel("Alpha:"));
		form.add(alpha);
		form.add(new JLabel("Grid Size (NxN):"));
		form.add(gridSize);
		//form.add(new JLabel("Grid Size Y:"));
		//form.add(gridSizeY);
		form.add(new JLabel("Scala:"));
		form.add(scala);
		form.add(new JLabel("Iteraions (-1 to infinite):"));
		form.add(cicles);
		ok = new JButton("OK");
		options.add(form, BorderLayout.CENTER);
		options.add(ok, BorderLayout.SOUTH);
	}

	private void accionCerrar() {
		int opcion = JOptionPane.showConfirmDialog(this, "Exit Q*Bert?", "Exit", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opcion == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

}
