# Ariel OOP - Ex2 The Maze of Waze

![graph1](https://github.com/ElielGez/Ex2/blob/master/images_examples/Capture.JPG)

## Introduction:
This project is part of assignment in Ariel University.
Main purpose of the project is to create directed weighted graph and display it on graphic user interface , 
and run algorithms on the graph.

## How to use GUI

* Create instance of GraphGUI class with width , height and graph object.

* Add by code nodes with Point3D (for location) and connect between edges , see examples below..

* Menu bar options:
	- File
		- Save: after drawing the graph , you can save it as txt file
		- Load: load the graph from txt file.
	- Algorithms
		- Shortest Path Distance: get the shortest path distance between 2 nodes (if path exist)
		- Shortest Path: get directions of the shortest path between 2 nodes (if path exist)
		- Graph connected?: check if the graph have strong conncetion
		- TSP: check if have path between targets of nodes
	
## Examples:

#### Monom Class:
```
Monom m = new Monom("5x^3");
double fx = m.f(1); //f(1) = 5 
```

#### Polynom Class:
```
Polynom p = new Polynom("-x+6-x^2");
double fx = p.f(3); //f(3) = -6
```

#### ComplexFunction Class:
```
String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
function f = new ComplexFunction().initFromString(s);
```

#### Drawing functions on graph:
```
Functions_GUI fg = new Functions_GUI();
String s = "mul(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)";
function f = new ComplexFunction().initFromString(s);
fg.add(f);
int w=1000, h=600, res=200;
Range rx = new Range(-10,10);
Range ry = new Range(-5,15);
fg.drawFunctions(w,h,rx,ry,res);
```

### **NOTE: More details about classes and interfaces of the project can be found on Wiki**
