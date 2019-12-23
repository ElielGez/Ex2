package testing;

import dataStructure.DGraph;
import dataStructure.Node;
import dataStructure.graph;
import gui.GraphGUI;
import utils.Point3D;

public class GraphGUITest {

	public static void main(String[] args) {
		DGraph g = new DGraph();
		g.addNode(new Node(new Point3D(150,300),0,"",0));
		g.addNode(new Node(new Point3D(100,140),0,"",0));
		g.addNode(new Node(new Point3D(70,50),0,"",0));
		g.addNode(new Node(new Point3D(50,250),0,"",0));
		g.addNode(new Node(new Point3D(170,70),0,"",0));
		g.addNode(new Node(new Point3D(200,20),0,"",0));
		g.addNode(new Node(new Point3D(230,50),0,"",0));
		g.addNode(new Node(new Point3D(250,150),0,"",0));
		g.addNode(new Node(new Point3D(280,280),0,"",0));
		g.addNode(new Node(new Point3D(300,300),0,"",0));
		g.connect(1, 3, 2);
		g.connect(2, 4, 5);
		g.connect(1, 5, 6);
		g.connect(3, 5, 6);
		g.connect(7, 10, 5.32);
		g.connect(3, 8, 5.32);
		GraphGUI gui = new GraphGUI(1000,1000,g);
		System.out.println(((DGraph)gui.g).verticesString());
		gui.setVisible(true);
	}

}
