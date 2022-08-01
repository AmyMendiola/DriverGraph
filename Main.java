/* Amy Mendiola
 * utdID: atm190002
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		final String DATA_FILE_NAME;
		final String ROUTES_FILE_NAME;
		
		//file and input operations
		Scanner scnr = new Scanner(System.in);
		System.out.println("Please input the name of the driver routes file."); //prompt user for file name
		DATA_FILE_NAME = scnr.next();
		File in = new File(DATA_FILE_NAME);
		Scanner input = new Scanner(in);
		System.out.println("Please input the name of the search and sort file."); //prompt user for file name
		ROUTES_FILE_NAME = scnr.next();
		
		Graph g = new Graph();
		
		if (in.canRead()) {
			readDataFile(input, g);
			File in2 = new File(ROUTES_FILE_NAME);
			Scanner input2 = new Scanner(in2);
			readRoutesFile(input2, g);
			input2.close();
		}
		input.close();
	}

	/*
	 * Read data file and create a graph
	 */
	private static void readDataFile(Scanner in, Graph g) {
		String line, vertex, edge;
		int weight;
		String[] list;
		
		while(in.hasNextLine()) {
			line = in.nextLine();
			vertex = line.substring(0, line.indexOf(" ")); //parse vertex
			line = line.substring(line.indexOf(" ") + 1);
			list = line.split("[, ]");
			g.insertVertex(vertex); //add vertex
			for (int i = 0; i < list.length; i+=2) {
				edge = list[i]; //parse edge
				weight = Integer.parseInt(list[i + 1]); //parse edge's weight
				g.insertEdge(vertex, edge, weight); //add edge
			}
		}
	}
	
	/* Read routes file and calculate weight of each route
	 * Sort and display drivers
	 */
	private static void readRoutesFile(Scanner in, Graph g) {
		String line, driver;
		int weight;
		boolean value;
		ArrayList<Driver> drivers = new ArrayList<>();
		while(in.hasNextLine()) {
			String[] route;
			line = in.nextLine();
			driver = line.substring(0, line.indexOf(" ")); //parse driver name
			line = line.substring(line.indexOf(" ") + 1);
			route = line.split(" ");
			weight = g.isRoute(route); //find weight of route
			Driver d = new Driver(driver, weight);
			drivers.add(d); //add driver to driver ArrayList
		}
		Collections.sort(drivers); //Sort drivers List
		//Display
		for (int i = 0; i < drivers.size(); i++) {
			System.out.print(drivers.get(i).toString());
		}
	}
}

class Driver implements Comparable<Driver>{
	String name;
	int weight;
	boolean valid;
		
	//Constructors
	public Driver(String n, int w) {
		name = n;
		weight = w;
		if (weight == 0) {
			valid = false;
		}
		else {
			valid = true;
		}
	}
		
	//Compare with weight value, if weight is the same
	//Compare with names in alphabetical
	@Override
	public int compareTo(Driver o) {
		int val = this.weight - o.weight;
		if (val == 0) {
			return this.name.compareTo(o.name);
		}
		return val;
	}
		
	//for Output format
	public String toString() {
		String output = name + "\t" + weight + "\t" ;
		if (valid == true) {
			output += "valid\n";
		}
		else {
			output += "invalid\n";
		}
		return output;
	}
}


 