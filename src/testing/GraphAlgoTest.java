package testing;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;

public class GraphAlgoTest {

	public static void main(String[] args) {
		graph g = new DGraph(20);
		g.connect(1, 3, 2);
		g.connect(2, 4, 5);
		g.connect(3, 5, 6);
		g.connect(7, 10, 5.32);
		g.connect(3, 8, 5.32);
		
		Graph_Algo ga = new Graph_Algo();
//		ga.init(g);
//		ga.save("test_file.txt");
		ga.init("test_file.txt");
		graph g1 = ga.getGraph();
		graph g2 = ga.copy();
		g1.removeNode(3);
		System.out.println(g1.nodeSize());
		System.out.println(g2.nodeSize());

	}

}
