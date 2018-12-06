package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class Wall extends RogueObject implements InteractableRogueObject{

	public Wall(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 4;
	}

	@Override
	public boolean canTake() {
		return false;
	}

}
