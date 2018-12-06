package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class Hammer extends RogueObject implements InteractableRogueObject{

	public Hammer(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean canTake() {
		// TODO Auto-generated method stub
		return true;
	}

}
