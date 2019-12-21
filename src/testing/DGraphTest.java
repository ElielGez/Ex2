package testing;

import dataStructure.DGraph;
import dataStructure.graph;

public class DGraphTest {

	public static void main(String[] args) {
		DGraph g = new DGraph(20);
		graph g2;
//		long start = System.currentTimeMillis();
		g.connect(1, 3, 2);
		g.connect(2, 4, 5);
		g.connect(3, 5, 6);
		g.connect(7, 10, 5.32);
		g.connect(3, 8, 5.32);
		g2 = new DGraph(g);
		g2.removeNode(3);
		System.out.println(g.edgeSize());
		System.out.println(g2.edgeSize());
//		long end = System.currentTimeMillis();
//		System.out.println(end - start);

	}

}
