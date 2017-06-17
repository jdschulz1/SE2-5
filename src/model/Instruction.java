package model;

/**
 * InstructionLineItems are individual lines on a list of Instructions to the Courier about how to traverse their entire delivery circuit.
 */
public class Instruction {

	public Instruction(int dist, Street currStreet, String currDirection, Street nextStreet, String nextDirection){
		this.distance = dist;
		this.currentStreet = currStreet;
		this.currentDirection = currDirection;
		this.nextStreet = nextStreet;
		this.nextDirection = nextDirection;
		
		this.CreateInstruction();
	}
	
	/**
	 * The distance in city blocks for the current InstructionsListItem until a turn occurs toward another Intersection.
	 */
	private int distance;
	/**
	 * The current Street that the Courier is on during this InstructionLineItem.  It is accompanied by a currentDirection that tells the current cardinal direction that the Courier is traveling on currentStreet.
	 */
	private Street currentStreet;
	/**
	 * The current direction that the Courier is traveling on the currentStreet for this InstructionsLineItem.
	 */
	private String currentDirection;
	/**
	 * The next Street, which the InstructionsLineItem is letting the Courier know to take after traveling the appropriate distance and turning in the correct cardinal direction, indicated by the nextDirection attribute.
	 */
	private Street nextStreet;
	/**
	 * The next direction, which the InstructionsLineItem is letting the Courier know to take down the nextStreet after traveling the appropriate distance on the currentStreet.
	 */
	private String nextDirection;
	
	/**
	 * Creates a string from the attributes to form a full instruction sentence.
	 */
	public String CreateInstruction() {
		String turnDir = "", instruction = "";
		if(this.currentDirection != null && this.nextDirection != null){
			if(this.currentDirection == "North"){
				if(this.nextDirection == "East"){
					turnDir = "Right";
				}
				else if(this.nextDirection == "West"){
					turnDir = "Left";
				}
			}
			else if(this.currentDirection == "South"){
				if(this.nextDirection == "East"){
					turnDir = "Left";
				}
				else if(this.nextDirection == "West"){
					turnDir = "Right";
				}
			}
			else if(this.currentDirection == "East"){
				if(this.nextDirection == "North"){
					turnDir = "Left";
				}
				else if(this.nextDirection == "South"){
					turnDir = "Right";
				}
			}
			else if(this.currentDirection == "West"){
				if(this.nextDirection == "North"){
					turnDir = "Right";
				}
				else if(this.nextDirection == "South"){
					turnDir = "Left";
				}
			}
		}

		String pluralDist = this.distance > 1 ? this.distance + " blocks " : this.distance + " block ";
		if(this.nextDirection == null){
			
			instruction = "Travel " + pluralDist + this.currentDirection + " on " + this.currentStreet.getName() + " and you will have arrived at your destination.";
		}
		else instruction = "Travel " +  pluralDist + this.currentDirection + " on " + this.currentStreet.getName() + " and turn " + turnDir + " on " + this.nextStreet.getName() + " heading " + this.nextDirection + ".";
		return instruction;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Street getCurrentStreet() {
		return currentStreet;
	}

	public void setCurrentStreet(Street currentStreet) {
		this.currentStreet = currentStreet;
	}

	public String getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(String currentDirection) {
		this.currentDirection = currentDirection;
	}

	public Street getNextStreet() {
		return nextStreet;
	}

	public void setNextStreet(Street nextStreet) {
		this.nextStreet = nextStreet;
	}

	public String getNextDirection() {
		return nextDirection;
	}

	public void setNextDirection(String nextDirection) {
		this.nextDirection = nextDirection;
	}

}