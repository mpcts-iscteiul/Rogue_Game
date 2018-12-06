package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class Key extends RogueObject implements InteractableRogueObject{

	public Key(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public boolean canTake() {
		return false;
	}

}
