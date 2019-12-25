package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JFrame;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
import utils.Point3D;

public class GraphGUI extends JFrame implements ActionListener, MouseListener {
	private graph g;
	private int mc;

	/**
	 * Empty constructor
	 */
	public GraphGUI() {
		this.g = new DGraph(20);
		this.mc = this.g.getMC();
		initGUI(700, 700);
	}

	/**
	 * Constructor with params
	 * @param width
	 * @param height
	 * @param g
	 */
	public GraphGUI(int width, int height, graph g) {
		this.g = g;
		this.mc = g.getMC();
		initGUI(width, height);
	}

	/**
	 * Getter for the graph
	 * @return
	 */
	public graph getG() {
		return this.g;
	}

	/**
	 * Getter for mc
	 * @return
	 */
	public int getMc() {
		return this.mc;
	}

	/**
	 * Function to init the gui object
	 * @param width
	 * @param height
	 */
	private void initGUI(int width, int height) {
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.initMcThread();
	}

	/**
	 * Function to draw the graph on the JFrame
	 */
	public void paint(Graphics g) {
		super.paint(g);
		this.drawGraph(g);
	}
	
	/**
	 * Function to init mc thread 
	 * This thread is listening to changes on the graph (by mc value)
	 */
	private void initMcThread() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					synchronized (g) {
						if(g.getMC() != mc) {
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
	 * @param g1
	 */
	private void drawGraph(Graphics g1) {
		Graphics2D g = (Graphics2D)g1;
		for (node_data src : this.g.getV()) {
			Point3D pSrc = src.getLocation();
			g.setColor(Color.BLUE);
			g.fillOval(pSrc.ix(), pSrc.iy(), 10, 10);
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
					g.setFont(new Font("Arial",Font.BOLD, 15));
					int centerX = (pSrc.ix() + pDest.ix()) / 2;
					int centerY = (pSrc.iy() + pDest.iy()) / 2;
					g.drawString("" + edge.getWeight(), centerX, centerY);
					for (int i = 0; i < 2; i++) {
						centerX = (centerX + pSrc.ix()) / 2;
						centerY = (centerY + pSrc.iy()) / 2;
					}
					g.setColor(Color.YELLOW);
					g.fillOval(centerX, centerY, 7, 7);
				}
			}
		}
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

//	@Override
//	public void actionPerformed(ActionEvent e) 
//	{
//		String str = e.getActionCommand();
//		
//		if(str.equals("Item 1"))
//		{
//			Point3D p1 = new Point3D(100,100);
//			Point3D p2 = new Point3D(50,300);
//			Point3D p3 = new Point3D(400,150);
//			
//			points.add(p1);
//			points.add(p2);
//			points.add(p3);
//			
//			repaint();
//		}
//		
//	}
}
