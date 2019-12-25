package algorithms;

import java.util.List;

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
	private graph g;

	/**
	 * Function to init the graph on this graph_algo object
	 */
	@Override
	public void init(graph g) {
		this.g = g;
	}

	/**
	 * Function to init the graph by file
	 */
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

	/**
	 * Function to save the graph object
	 */
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

	/**
	 * Algorithm to check if the graph is fully connected
	 */
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

	/**
	 * Algorithm to find the shortes path distance between src and dest
	 */
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

	/**
	 * Function to get the minimum node by weight
	 * @return
	 */
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

	/**
	 * Algorithm to get the shorest path in oredered list
	 */
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

	/**
	 * Function to reverse list
	 * @param list
	 * @return
	 */
	private static LinkedList<node_data> reverseList(LinkedList<node_data> list) {
		LinkedList<node_data> rList = new LinkedList<node_data>();
		while (!list.isEmpty()) {
			rList.add(list.getLast());
			list.removeLast();
		}
		return rList;
	}

	/**
	 * Algorithm that computes a relatively short path which visit each node in the targets List
	 */
	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if(!this.isConnected()) return null;
		LinkedList<node_data> list = new LinkedList<node_data>();
		for (int i = 0; i < targets.size() - 1; i++) {
			list.addAll(shortestPath(targets.get(i), targets.get(i+1)));
		}
		return list;
	}

	/**
	 * Deep copy function
	 */
	@Override
	public graph copy() {
		return this.g.copy();
	}

	/**
	 * Getter for the graph
	 * @return
	 */
	public graph getGraph() {
		return this.g;
	}

}
