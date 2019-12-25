package dataStructure;

import java.util.Collection;
import java.util.LinkedHashMap;

public class DGraph implements graph {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LinkedHashMap<Integer, node_data> vertices;
	private LinkedHashMap<Integer, LinkedHashMap<Integer, edge_data>> edges;
	private int mc;
	private int edgesSize;

	public DGraph() {
		this.vertices = new LinkedHashMap<Integer, node_data>();
		this.edges = new LinkedHashMap<Integer, LinkedHashMap<Integer, edge_data>>();
	}

	@SuppressWarnings("unchecked")
	public DGraph(DGraph g) {
		this.vertices = (LinkedHashMap<Integer, node_data>) g.vertices.clone();
		this.edges = (LinkedHashMap<Integer, LinkedHashMap<Integer, edge_data>>) g.edges.clone();
		this.mc = g.getMC();
		this.edgesSize = g.edgeSize();

	}

	public DGraph(int vertices) {
		this();
		for (int i = 0; i < vertices; i++) {
			node_data n = new Node();
			this.vertices.put(n.getKey(), n);
		}
	}

	@Override
	public node_data getNode(int key) {
		return this.vertices.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return this.edges.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.vertices.put(n.getKey(), n);
		this.mc++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		node_data s = this.vertices.get(src);
		node_data d = this.vertices.get(dest);
		if (s == null || d == null) {
			System.out.println("Cannot connect between this vertices , some of them or maybe both , are null");
			return;
		}
		LinkedHashMap<Integer, edge_data> hp = this.edges.get(src);
		if (hp == null) {
			// new src , create new linked list of edge values.
			hp = new LinkedHashMap<Integer, edge_data>();
		}
		edge_data e = new Edge(src, dest, w);
		hp.put(dest, e);
		this.edges.put(src, hp);
		this.edgesSize++;
		this.mc++;
	}

	@Override
	public Collection<node_data> getV() {
		return this.vertices.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		LinkedHashMap<Integer, edge_data> edgesSrc = this.edges.get(node_id);
		if (edgesSrc == null)
			return null;
		return edgesSrc.values();
	}

	@Override
	public node_data removeNode(int key) {
		LinkedHashMap<Integer, edge_data> e = this.edges.remove(key);
		node_data n = this.vertices.remove(key);

		if (e.values() != null && n != null)
			this.mc++;

		if (e.values() != null)
			this.edgesSize -= e.size();

		return n;

	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		edge_data e = this.edges.get(src).remove(dest);
		if (e != null) {
			this.edgesSize--;
			this.mc++;
		}

		return e;
	}

	@Override
	public int nodeSize() {
		return this.vertices.size();
	}

	@Override
	public int edgeSize() {
		return this.edgesSize;
	}

	@Override
	public int getMC() {
		return this.mc;
	}

	public String verticesString() {
		String s = "";
		for (int key : this.vertices.keySet()) {
			s += this.vertices.get(key) + "\n";
		}
		return s;
	}

	public String toString() {
		String s = "";
		for (int src : this.edges.keySet()) {
			LinkedHashMap<Integer, edge_data> edgeSrc = this.edges.get(src);
			if (edgeSrc != null) {
				for (int dest : edgeSrc.keySet()) {
					s += "HashMap data - src: " + src + " dest: " + dest + "\n" + edgeSrc.get(dest) + "\n\n";
				}
			}
		}
		return s;
	}

	@Override
	public graph copy() {
		return new DGraph(this);
	}

	@Override
	public void initNodesGraph() {
		for (node_data n : this.getV()) {
			n.initNode();
		}

	}

}
