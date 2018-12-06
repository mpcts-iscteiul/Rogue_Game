package pt.iscte.poo.utils;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class RogueObject implements ImageTile {

	private Point2D position;

	public RogueObject(Point2D position) {
		this.position = position;
	}	

	@Override
	public Point2D getPosition() {
		return position;
	}
	
	protected void setPosition(Point2D newPosition) {
		this.position = newPosition; 
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
	

}
