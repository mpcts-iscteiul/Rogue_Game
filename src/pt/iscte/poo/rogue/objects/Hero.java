
package pt.iscte.poo.rogue.objects;

import pt.iscte.poo.rogue.Rogue;
import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.MovingRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Hero extends RogueObject implements InteractableRogueObject {
																			

	private int atackForce, life;

	public Hero(Point2D position) {
		super(position);
		atackForce = 1;
		life = 10;
	}

	public void move(Direction d) {
		Point2D nextP = getPosition().plus(d.asVector());
		if (canMove(nextP)){
			setPosition(nextP);
		}else if(Rogue.getInstance().isEnemy(nextP)){
			MovingRogueObject aux = Rogue.getInstance().getEnemy(nextP);
			aux.setDano(atackForce);
		}else if(Rogue.getInstance().isDoor(nextP)){
			Door door = Rogue.getInstance().getDoor(nextP);
			if(door.getName().equals("DoorClosed"))
				door.open();
			else{
				Rogue.getInstance().entry(door);
				setPosition(new Point2D(1,1));
			}
		}
		
			
	}

	@Override
	public int getLayer() {
		return 3;
	}
	
	public void setDano(){
		life--;
		Rogue.getInstance().statusBarUpdate(life);
	}

	@Override
	public boolean canTake() {
		for (RogueObject o : Rogue.getInstance().getObjectList()) {
			if (o.getPosition().equals(getPosition())){
				
			}
			
		}
		return false;
	}


	public boolean canMove(Point2D point) {
		for (RogueObject o : Rogue.getInstance().getObjectList()) {
			if (o.getPosition().equals(point) && o.getLayer() > 2)
				return false;
		}
		return true;
	}
}
