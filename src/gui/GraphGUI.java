package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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
	public graph g;

	public GraphGUI() {
		g = new DGraph(20);
		initGUI(700, 700);
	}

	public GraphGUI(int width, int height, graph g) {
		this.g = g;
		initGUI(width, height);
	}

	private void initGUI(int width, int height) {
		this.setSize(width,height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.drawGraph(g);
	}
	
	private void drawGraph(Graphics g) {
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
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
//		System.out.println("mouseClicked");
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		int x = e.getX();
//		int y = e.getY();
//		Point3D p = new Point3D(x,y);
//		points.add(p);
//		repaint();
//		System.out.println("mousePressed");
//		
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		System.out.println("mouseReleased");
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		System.out.println("mouseEntered");
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		System.out.println("mouseExited");
//	}
}
