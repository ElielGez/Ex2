package dataStructure;

import java.util.Random;

import utils.Point3D;

public class Node implements node_data {
	private int key;
	private Point3D location;
	private double weight;
	private String info;
	private int tag;
	static int uuid = 1;

	public Node() {
		setKey();
		this.initNode();
	}
	
	public Node(node_data n) {
		this.key = n.getKey();
		this.weight = n.getWeight();
		this.info = n.getInfo();
		this.location = n.getLocation();
		this.tag = n.getTag();
	}

	//need this?
	public Node(Point3D location, double weight, String info, int tag) {
		setKey();
		this.location = new Point3D(location);
		this.weight = weight;
		this.info = info;
		this.tag = tag;
	}

	@Override
	public int getKey() {
		return this.key;
	}

	private void setKey() {
		this.key = uuid;
		uuid++;
	}

	@Override
	public Point3D getLocation() {
		return new Point3D(this.location);
	}

	@Override
	public void setLocation(Point3D p) {
		this.location = new Point3D(p);
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
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
		return "Node data - " + " key: " + this.key + " location: " + this.location + " weight: " + this.weight + " info: "
				+ this.info + " tag: " + this.tag;
	}
	
	private static Point3D getRandomLocation() {
		int randomX = new Random().nextInt(400);
		int randomY = new Random().nextInt(400);
		return new Point3D(randomX,randomY);
	}
	
	public void initNode() {
		setTag(0);
		setInfo("");
		setWeight(Double.MAX_VALUE);
		setLocation(getRandomLocation());
	}

}
