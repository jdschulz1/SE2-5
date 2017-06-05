package model;

/**
 * InstructionLineItems are individual lines on a list of Instructions to the Courier about how to traverse their entire delivery circuit.
 */
public class Instruction {

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
		// TODO - implement Instruction.CreateInstruction
		throw new UnsupportedOperationException();
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