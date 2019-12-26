package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class GraphGUI extends JFrame implements ActionListener, MouseListener {
	private graph g;
	private int mc;
	private Graph_Algo ga = new Graph_Algo();

	private static final String SAVE = "Save";
	private static final String LOAD = "Load";
	private static final String SHORTEST_PATH_DISTANCE = "Shortest Path Distance";
	private static final String SHORTEST_PATH = "Shortest Path";
	private static final String IS_CONNECTED = "Graph connected?";
	private static final String TSP = "TSP";

	/**
	 * Empty constructor
	 */
	public GraphGUI() {
		this.g = new DGraph(20);
		this.mc = this.g.getMC();
		this.ga.init(this.g);
		initGUI(700, 700);
	}

	/**
	 * Constructor with params
	 * 
	 * @param width
	 * @param height
	 * @param g
	 */
	public GraphGUI(int width, int height, graph g) {
		if (g == null)
			throw new RuntimeException("graph cannot be null");
		this.g = g;
		this.mc = g.getMC();
		this.ga.init(this.g);
		initGUI(width, height);
	}

	/**
	 * Getter for the graph
	 * 
	 * @return
	 */
	public graph getG() {
		return this.g;
	}

	/**
	 * Getter for mc
	 * 
	 * @return
	 */
	public int getMc() {
		return this.mc;
	}

	/**
	 * Function to init the gui object
	 * 
	 * @param width
	 * @param height
	 */
	private void initGUI(int width, int height) {
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.initMenuBar();
		this.initMcThread();
	}

	private void initMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu file = new Menu("File");
		Menu algo = new Menu("Algorithms");
		menuBar.add(file);
		menuBar.add(algo);
		this.setMenuBar(menuBar);

		MenuItem save = new MenuItem(SAVE);
		save.addActionListener(this);
		MenuItem load = new MenuItem(LOAD);
		load.addActionListener(this);

		MenuItem spd = new MenuItem(SHORTEST_PATH_DISTANCE);
		spd.addActionListener(this);
		MenuItem sp = new MenuItem(SHORTEST_PATH);
		sp.addActionListener(this);
		MenuItem ic = new MenuItem(IS_CONNECTED);
		ic.addActionListener(this);
		MenuItem tsp = new MenuItem(TSP);
		tsp.addActionListener(this);

		file.add(save);
		file.add(load);
		algo.add(spd);
		algo.add(sp);
		algo.add(ic);
		algo.add(tsp);

		this.addMouseListener(this);
	}

	/**
	 * Function to draw the graph on the JFrame
	 */
	public void paint(Graphics g) {
		super.paint(g);
		this.drawGraph(g);
	}

	/**
	 * Function to init mc thread This thread is listening to changes on the graph
	 * (by mc value)
	 */
	private void initMcThread() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					synchronized (g) {
						if (g.getMC() != mc) {
							mc = g.getMC();
							repaint();
						}
					}
				}
			}
		});
		t.start();
	}

	/**
	 * Function that draw the graph with his nodes and edges
	 * 
	 * @param g1
	 */
	private void drawGraph(Graphics g1) {
		Graphics2D g = (Graphics2D) g1;
		for (node_data src : this.g.getV()) {
			Point3D pSrc = src.getLocation();
			g.setColor(Color.BLUE);
			g.fillOval(pSrc.ix(), pSrc.iy() - 3, 10, 10);
			g.drawString("" + src.getKey(), pSrc.ix(), pSrc.iy() - 3);

			Collection<edge_data> e = this.g.getE(src.getKey());
			if (e != null) {
				for (edge_data edge : e) {
					node_data dest = this.g.getNode(edge.getDest());
					Point3D pDest = dest.getLocation();
					g.setColor(Color.RED);
					g.setStroke(new BasicStroke(3));
					g.drawLine(pSrc.ix(), pSrc.iy(), pDest.ix(), pDest.iy());

					g.setColor(Color.DARK_GRAY);
					g.setFont(new Font("Arial", Font.BOLD, 15));
					int centerX = (pSrc.ix() + pDest.ix()) / 2;
					int centerY = (pSrc.iy() + pDest.iy()) / 2;
					g.drawString("" + edge.getWeight(), centerX, centerY);
					for (int i = 0; i < 2; i++) {
						centerX = (centerX + pSrc.ix()) / 2;
						centerY = (centerY + pSrc.iy()) / 2;
					}
					g.setColor(Color.DARK_GRAY);
					g.fillOval(centerX, centerY, 7, 7);
				}
			}
		}
	}

	private String loadSaveDialog(String text, int mode) {
		FileDialog fd = new FileDialog(this, text, mode);
		fd.setFile("*.txt");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		fd.setVisible(true);
		return fd.getDirectory() + fd.getFile();
	}

	/**
	 * Function to handle shortest path menu choose
	 * 
	 * @param mode
	 */
	private void shortestPathGUI(String mode) {
		String source = JOptionPane.showInputDialog("Please insert source node key");
		String destination = JOptionPane.showInputDialog("Please insert destination node key");
		int src, dest;
		try {
			src = Integer.parseInt(source);
			dest = Integer.parseInt(destination);
			node_data nSrc = this.g.getNode(src);
			if (nSrc == null)
				throw new RuntimeException("No such source node exist , key: " + src);
			node_data nDest = this.g.getNode(dest);
			if (nDest == null)
				throw new RuntimeException("No such destination node exist , key: " + src);

			if (mode == "DISTANCE") {
				double result = this.ga.shortestPathDist(src, dest);
				if (result == Double.MAX_VALUE)
					JOptionPane.showMessageDialog(null, "There is no path between this nodes");
				else
					JOptionPane.showMessageDialog(null, "The shortest path distance is : " + result);
			} else if (mode == "LIST") {
				List<node_data> list = this.ga.shortestPath(src, dest);

				JOptionPane.showMessageDialog(null, "The path is : " + getPathFromList(list));
			}
		} catch (NumberFormatException ex) {
			System.out.println("Please insert numbers only :" + ex);
		}
	}

	/**
	 * Function to get the path as string from list of nodes
	 * 
	 * @param list
	 * @return
	 */
	private static String getPathFromList(List<node_data> list) {
		if (list == null)
			return "No path found";
		String path = "";
		for (node_data n : list) {
			path += n.getKey() + ">";
		}
		path = path.substring(0, path.length() - 1);
		return path;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Override action listener function , to listen the menu clicks
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		switch (str) {
		case SAVE: {
			String pathname = loadSaveDialog("Save the text file", FileDialog.SAVE);
			this.ga.save(pathname);
			break;
		}
		case LOAD: {
			String pathname = loadSaveDialog("Load the text file", FileDialog.LOAD);
			this.ga.init(pathname);
			this.g = this.ga.copy();
			repaint();
			break;
		}
		case SHORTEST_PATH_DISTANCE: {
			this.shortestPathGUI("DISTANCE");
			break;
		}
		case SHORTEST_PATH: {
			this.shortestPathGUI("LIST");
			break;
		}
		case IS_CONNECTED: {
			JOptionPane.showMessageDialog(null, this.ga.isConnected() ? "True" : "False");
			break;
		}
		case TSP: {
			String s = JOptionPane.showInputDialog("Please insert amount of targets");
			int amount;
			try {
				amount = Integer.parseInt(s);
				List<Integer> targets = new LinkedList<Integer>();
				for (int i = 1; i <= amount; i++) {
					String numStr = JOptionPane.showInputDialog("Please insert target #" + i);
					int num = Integer.parseInt(numStr);
					targets.add(num);
				}
				List<node_data> list = this.ga.TSP(targets);
				JOptionPane.showMessageDialog(null, "The path is : " + getPathFromList(list));

			} catch (NumberFormatException ex) {
				System.out.println("Please insert numbers only :" + ex);
			}
			break;
		}

		}
	}
}
