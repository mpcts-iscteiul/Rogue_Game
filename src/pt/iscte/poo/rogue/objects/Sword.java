package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class Sword extends RogueObject implements InteractableRogueObject{

	public Sword(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean canTake() {
		return false;
	}

}
