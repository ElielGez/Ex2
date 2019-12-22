package algorithms;

import java.util.List;

import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node_data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
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
