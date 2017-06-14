package model;

public class Edge {

	
	public Edge(Street s, Vertex v, Vertex v2){
		this.source = v;
		this.destination = v2;
		this.street = s;
	}
	
	public Vertex getSource() {
		return source;
	}

	public void setSource(Vertex source) {
		this.source = source;
	}

	public Vertex getDestination() {
		return destination;
	}

	public void setDestination(Vertex destination) {
		this.destination = destination;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	private Vertex source, destination;
	private Street street;
}
