package testing;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import gui.GraphGUI;
import utils.Point3D;

public class GraphGUITest {

	public static void main(String[] args) throws InterruptedException {
		DGraph g = new DGraph();
		g.addNode(new Node(1));
		g.addNode(new Node(2));
		g.addNode(new Node(3));
		g.connect(1, 3, 14);
		g.connect(1, 4, 9);
		g.connect(1, 6, 7);
		g.connect(3, 2, 9);
		g.connect(3, 4, 2);
		g.connect(4, 1, 2);
		g.connect(4, 3, 2);
		g.connect(4, 5, 11);
		g.connect(4, 6, 10);
		g.connect(5, 2, 6);
		g.connect(6, 5, 15);

//		int million = 1000000;
//		graph g = new DGraph(million);
//		for (node_data n : g.getV()) {
//			for (int i = 1; i <= 10; i++) {
//				g.connect(n.getKey(), (n.getKey() + i) % million + 1, i * 5);
//			}
//		}
		GraphGUI gui = new GraphGUI(g);
	}

}
