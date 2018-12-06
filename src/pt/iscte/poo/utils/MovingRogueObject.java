package pt.iscte.poo.utils;

import pt.iul.ista.poo.utils.Point2D;

public interface MovingRogueObject {
	public void move();
	public boolean canMove(Point2D point);
	public void setDano(int dano);
}
