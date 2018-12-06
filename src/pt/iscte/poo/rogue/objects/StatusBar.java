package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class StatusBar extends RogueObject {

	private String name;

	public StatusBar(Point2D position) {
		super(position);
		name = "Black";
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return name;
	}

	public void setGreen() {
		name = "Green";
	}

	public void setRed() {
		if (name.equals("Green"))
			name = "RedGreen";
		else
			name = "Red";
	}

}
