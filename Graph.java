/* Amy Mendiola
 * utdID: atm190002
 */
import java.util.ArrayList;

public class Graph {
	int size;
	ArrayList<ArrayList<Node>> edgesList;
	ArrayList<String> vertexList;
	
	//Constructors
	public Graph(int s) {
		edgesList = new ArrayList<ArrayList<Node>>();
		vertexList = new ArrayList<String>();
		size = s;
	}
	
	public Graph() {
		this(0);
	}
	
	//Getters
	public int getSize() {
		return size;
	}
	public ArrayList<ArrayList<Node>> getEdgesList() {
		return edgesList;
	}
	public ArrayList<String> getVertexList() {
		return vertexList;
	}
	
	//Setters
	public void setSize(int size) {
		this.size = size;
	}
	
	//Insert Edge into Graph
	public void insertEdge(String vertex, String edge, int weight) {
		int index = vertexList.indexOf(vertex); //get index of vertex
		Node n = new Node(edge,weight);
		edgesList.get(index).add(n); //add edge into vertex list
		if (edgesList.get(index).indexOf(null) == 0) { //remove null blank in ArrayList
			edgesList.get(index).remove(0);
		}
	}
	
	//Insert Vertex into Graph
	public void insertVertex(String vertex) {
		vertexList.add(vertex); //add vertex to vertex List
		//add blank ArrayList to EdgesList for the new vertex
		ArrayList<Node> blank = new ArrayList<>();
		blank.add(null);
		edgesList.add(blank);
	}
	
	//Delete Edge from Graph
	public void deleteEdge(String vertex, String edge) {
		int index = vertexList.indexOf(vertex); //get index of vertex in vertexList
		int eIndex = 0;
		Node n = new Node(edge,0);
		for (int i = 0; i < edgesList.get(index).size(); i++) { //find index of edge in edges List
			if (n.equals(edgesList.get(index).get(i))) {
				eIndex = i;
			}
		}
		edgesList.get(index).remove(eIndex); //remove edge
	}
	
	//Delete Vertex from Graph
	public void deleteVertex(String vertex) {
		int index = vertexList.indexOf(vertex); //find index of vertex
		//delete both vertex in vertexList and the adjacency list
		vertexList.remove(index);
		edgesList.remove(index);
	}
	
	//Determine if Route is Possible
	public int isRoute(String[] route) {
		int weight = 0;
		int sIndex, currIndex;
		for (int i = 1; i < route.length; i++) { //for each route
			currIndex = -1;
			sIndex = vertexList.indexOf(route[i - 1]); //get index of previous location
			if (sIndex == -1) { //if does not exist, return false 
				return 0;
			}
			Node n = new Node(route[i], 0);
			for (int k = 0; k < edgesList.get(sIndex).size(); k++) { //loop to find location in edgesList
				if (n.equals(edgesList.get(sIndex).get(k))) {
					currIndex = k;
				}
			}
			if (currIndex == -1) { //if location not found, return false
				return 0;
			}
			//if found add weight of edge
			weight+= edgesList.get(sIndex).get(currIndex).getWeight();
		}
		return weight;
	}

	/*
	 * Node class to hold name and weight of edge
	 */
	class Node{
		String name;
		int weight;
		
		//Constructors
		public Node() {
			
		}
		
		public Node(String n, int w) {
			name = n;
			weight = w;
		}
		
		//Getters
		public String getName() {
			return name;
		}
		
		public int getWeight() {
			return weight;
		}
		
		//Setters
		public void setName(String name) {
			this.name = name;
		}
		
		public void setWeight(int weight) {
			this.weight = weight;
		}
	
		//Nodes are equal if name is the same
		public boolean equals(Node n) {
			if (this.name.equals(n.name)) {
				return true;
			}
			return false;
		}
	}
}

	
	
