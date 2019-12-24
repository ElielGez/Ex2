package testing;

import java.awt.List;
import java.util.LinkedList;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;

public class GraphAlgoTest {

	public static void main(String[] args) {
		graph g = new DGraph(6);
		g.connect(1, 3, 14);
		g.connect(1, 4, 9);
		g.connect(1, 6, 7);
		g.connect(2, 3, 7);
		g.connect(2, 5, 7);
		g.connect(3, 2, 9);
		g.connect(3, 4, 2);
		g.connect(4, 1, 2);
		g.connect(4, 3, 2);
		g.connect(4, 5, 11);
		g.connect(4, 6, 10);
		g.connect(5, 2, 6);
		g.connect(6, 5, 15);
		
		Graph_Algo ga = new Graph_Algo();
//		ga.init(g);
		System.out.println(ga.shortestPathDist(1, 2));
//		System.out.println(ga.isConnected());
//		LinkedList<Integer> l = new LinkedList<Integer>();
//		l.add(1);
//		l.add(4);
//		l.add(2);
//		
//		LinkedList<node_data> list = (LinkedList<node_data>) ga.TSP(l);
//		System.out.println(list);

	}

}
