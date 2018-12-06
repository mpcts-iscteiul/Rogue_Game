package pt.iscte.poo.rogue.objects;

import java.util.Random;

import pt.iscte.poo.rogue.Rogue;
import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.MovingRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Bat extends RogueObject implements InteractableRogueObject, MovingRogueObject {

	private Random random;
	private int move, life;

	public Bat(Point2D position) {
		super(position);
		random = new Random();
		life = 3;
	}

	@Override
	public int getLayer() {
		return 3;
	}

	@Override
	public void move() {
		move = random.nextInt(99);
		int x = Math.abs(getPosition().getX() - Rogue.getInstance().getHero().getPosition().getX());
		int y = Math.abs(getPosition().getY() - Rogue.getInstance().getHero().getPosition().getY());
		if ((x == 1 && y == 0) || (x == 0 && y == 1)) {
			if (move > 49){//50% hipotse de atacar
				Hero aux = (Hero)Rogue.getInstance().getHero();
				aux.setDano();
			}
		} else {
			if (move > 49) {//mover em direcao ao heroi
				Vector2D vector = Vector2D.movementVector(getPosition(), Rogue.getInstance().getHero().getPosition());
				if (canMove(getPosition().plus(vector))) {
					setPosition(getPosition().plus(vector));
				}
			} else {//mover aleatorio
				Point2D point = getPosition().plus(Direction.random().asVector());
				if (canMove(point))
					setPosition(point);
			}
		}
	}

	@Override
	public void setDano(int dano) {
		life = life-dano;
		if(life<0)
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
