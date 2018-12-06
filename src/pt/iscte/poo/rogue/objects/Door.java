package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class Door extends RogueObject implements InteractableRogueObject{

	String name, goToRoom, goToDoor;
	int number;
	
	public Door(Point2D position, int number, String goToRoom, String goToDoor) {
		super(position);
		this.name="DoorClosed";
		this.number = number;
		this.goToRoom = goToRoom;
		this.goToDoor = goToDoor;	
	}

	@Override
	public int getLayer() {
		return 5;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public boolean canTake() {
		return false;
	}

	public void open() {
		this.name="DoorOpen";
	}

	public String getGoToRoom() {
		return goToRoom;
	}

	public String getGoToDoor() {
		return goToDoor;
	}

	public int getNumber() {
		return number;
	}
	
	

}
