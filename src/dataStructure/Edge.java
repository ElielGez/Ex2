package dataStructure;

public class Edge implements edge_data {
	private int src;
	private int dest;
	private double weight;
	private String info;
	private int tag;
	
	public Edge(int src,int dest,double weight) {
		this.src = src;
		this.dest = dest;
		this.weight = weight;
	}
	
	public Edge(edge_data e) {
		this.src = e.getSrc();
		this.dest = e.getDest();
		this.weight = e.getWeight();
		this.info = e.getInfo();
		this.tag = e.getTag();
	}

	@Override
	public int getSrc() {
		return this.src;
	}

	@Override
	public int getDest() {
		return this.dest;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}
	
	public String toString() {
		return "Edge data - " + " src: " + this.src + " dest: " + this.dest + " weight: " + this.weight + " info: "
				+ this.info + " tag: " + this.tag + " ";
	}

}
