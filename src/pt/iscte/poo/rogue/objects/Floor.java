package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class Floor extends RogueObject implements InteractableRogueObject{

	public Floor(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 0;
	}

	@Override
	public boolean canTake() {
		return false;
	}

}
