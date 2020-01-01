package Tests;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import dataStructure.node_data;
import gui.GraphGUI;

public class GraphGUITest {

	public static void main(String[] args) throws InterruptedException {
//		DGraph g = new DGraph();
//		g.addNode(new Node(1));
//		g.addNode(new Node(2));
//		g.addNode(new Node(3));
//		g.connect(1, 3, 14);
//		g.connect(3, 2, 9);

		int million = 30;
		graph g = new DGraph(million);
		for (node_data n : g.getV()) {
			for (int i = 1; i <= 3; i++) {
				g.connect(n.getKey(), (n.getKey() + i) % million + 1, i * 5);
			}
		}
		GraphGUI gui = new GraphGUI(g);
	}

}
