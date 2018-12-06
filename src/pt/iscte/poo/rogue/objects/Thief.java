package pt.iscte.poo.rogue.objects;

import java.util.Random;

import pt.iscte.poo.rogue.Rogue;
import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.MovingRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Thief extends RogueObject implements InteractableRogueObject, MovingRogueObject {

	private Random random;
	private int thiev;
	private boolean holdingObject;
	private String object;
	private RogueObject obj;

	public Thief(Point2D position) {
		super(position);
		holdingObject = false;
	}

	@Override
	public int getLayer() {
		return 3;
	}

	@Override
	public void move() {
		int x = Math.abs(getPosition().getX() - Rogue.getInstance().getHero().getPosition().getX());
		int y = Math.abs(getPosition().getY() - Rogue.getInstance().getHero().getPosition().getY());
		if (holdingObject) {//Fugir do hero
			Vector2D vector = Vector2D.movementVector(getPosition(), Rogue.getInstance().getHero().getPosition());
			if (canMove(getPosition().plus(vector))) {
				setPosition(getPosition().plus(vector));
			}
		} else {
			if ((x == 1 && y == 0) || (x == 0 && y == 1)) {//tenta roubar
				thiev = random.nextInt(99);
				if(thiev>49){
					Hero aux = (Hero) Rogue.getInstance().getHero();
				}
			} else {//mover para hero
				Vector2D vector = Vector2D.movementVector(getPosition(), Rogue.getInstance().getHero().getPosition());
				if (canMove(getPosition().plus(vector))) {
					setPosition(getPosition().plus(vector));
				}
			}
		}
	}

	@Override
	public void setDano(int dano) {
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
