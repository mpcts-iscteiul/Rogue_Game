package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.rogue.Rogue;
import pt.iscte.poo.utils.*;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Skeleton extends RogueObject implements InteractableRogueObject, MovingRogueObject {

	private int move, x, y, life;

	public Skeleton(Point2D position) {
		super(position);
		move = 0;
		life = 5;
	}

	@Override
	public int getLayer() {
		return 3;
	}

	@Override
	public void move() {
		move++;
		if (move % 2 == 0) {
			x = Math.abs(getPosition().getX() - Rogue.getInstance().getHero().getPosition().getX());
			y = Math.abs(getPosition().getY() - Rogue.getInstance().getHero().getPosition().getY());
			if ((x == 1 && y == 0) || (x == 0 && y == 1)) {
				Hero aux = (Hero) Rogue.getInstance().getHero();
				aux.setDano();
			} else {
				Vector2D vector = Vector2D.movementVector(getPosition(), Rogue.getInstance().getHero().getPosition());
				if (canMove(getPosition().plus(vector))) {
					setPosition(getPosition().plus(vector));
				}
			}
		}
	}

	@Override
	public void setDano(int dano) {
		life = life - dano;
		if (life < 0)
			Rogue.getInstance().remove(this);
	}

	@Override
	public boolean canTake() {
		return false;
	}

	@Override
	public boolean canMove(Point2D point) {
		for (RogueObject o : Rogue.getInstance().getObjectList()) {
			if (o.getPosition().equals(point) && o.getLayer() > 2)
				return false;
		}
		return true;
	}
	
}
