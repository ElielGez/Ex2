package algorithms;

import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * This empty class represents the set of graph-theory algorithms which should
 * be implemented as part of Ex2 - Do edit this class.
 * 
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms {
	public static final Comparator<node_data> _Comp = new VertexComparator();
	private graph g;

	@Override
	public void init(graph g) {
		this.g = g;
	}

	@Override
	public void init(String file_name) {
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			this.g = (graph) in.readObject();

			in.close();
			file.close();

		}

		catch (IOException ex) {
			System.out.println(ex);
		}

		catch (ClassNotFoundException ex) {
			System.out.println(ex);
		}

	}

	@Override
	public void save(String file_name) {
		try {
			FileOutputStream file = new FileOutputStream(file_name);
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this.g);
			out.close();
			file.close();

		} catch (IOException ex) {
			System.out.println(ex);
		}

	}

	@Override
	public boolean isConnected() {
		for (node_data src : this.g.getV()) {
			for (node_data dest : this.g.getV()) {
				if (shortestPathDist(src.getKey(), dest.getKey()) == Double.MAX_VALUE)
					return false;
				if (shortestPathDist(dest.getKey(), src.getKey()) == Double.MAX_VALUE)
					return false;
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		try {
			this.g.initNodesGraph();
			node_data nSrc = this.g.getNode(src);
			nSrc.setWeight(0);
			while (nSrc.getTag() == 0 && nSrc.getKey() != dest) { // didn't visit this node yet
				Collection<edge_data> e = this.g.getE(nSrc.getKey());
				if (e != null) {
					for (edge_data edge : e) {
						node_data nDest = this.g.getNode(edge.getDest());
						double sum = nSrc.getWeight() + edge.getWeight();
						if (nDest.getTag() == 0 && sum < nDest.getWeight()) {
							nDest.setWeight(sum);
							nDest.setInfo("" + nSrc.getKey());
						}
					}
				}
				nSrc.setTag(1);
				nSrc = getMinimumNode();
			}
			return nSrc.getWeight();
		}
		catch (NullPointerException e) {
			System.out.println("Please init the graph with nodes and edges first");
		}
		catch (RuntimeException e) {
			System.out.println("Invalid src or dest");
		}
		return 0;
	}

	private node_data getMinimumNode() {
		node_data n = null;
		for (node_data node : this.g.getV()) {
			if (node.getTag() == 0) {
				if (n == null)
					n = node;
				if (node.getWeight() < n.getWeight())
					n = node;
			}
		}
		return n;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		LinkedList<node_data> list = new LinkedList<node_data>();
		this.shortestPathDist(src, dest);
		node_data nDest = this.g.getNode(dest);
		while (nDest.getKey() != src) {
			list.add(nDest);
			nDest = this.g.getNode(Integer.parseInt(nDest.getInfo()));
		}
		list.add(this.g.getNode(src));

		return reverseList(list);
	}

	private static LinkedList<node_data> reverseList(LinkedList<node_data> list) {
		LinkedList<node_data> rList = new LinkedList<node_data>();
		while (!list.isEmpty()) {
			rList.add(list.getLast());
			list.removeLast();
		}
		return rList;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		LinkedList<node_data> list = new LinkedList<node_data>();
		for (int i = 0; i < targets.size() - 1; i++) {
			list.addAll(shortestPath(targets.get(i), targets.get(i+1)));
		}
		return list;
	}

	@Override
	public graph copy() {
		return this.g.copy();
	}

	public graph getGraph() {
		return this.g;
	}

}

class VertexComparator implements Comparator<node_data> {

	public VertexComparator() {
		;
	}

	public int compare(node_data n1, node_data n2) {
		int ans = 0;
		if (n1.getWeight() - n2.getWeight() > 0)
			ans = 1;
		else if (n1.getWeight() - n2.getWeight() < 0)
			ans = -1;
		return ans;
	}

}
