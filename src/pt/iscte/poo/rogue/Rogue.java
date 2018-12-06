package pt.iscte.poo.rogue;

import java.util.ArrayList;

import pt.iscte.poo.rogue.objects.Door;
import pt.iscte.poo.rogue.objects.Hero;
import pt.iscte.poo.rogue.objects.StatusBar;
import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.MovingRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Rogue implements Observer {

	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;

	private Hero hero;
	private BuilderRogueObjects scene;
	private ArrayList<RogueObject> objectList;
	private ArrayList<MovingRogueObject> enemyList;
	private ArrayList<InteractableRogueObject> interactableList;
	private int score, highscore, level;
	private boolean gameOver = false;

	// Singleton pattern attribute
	private static final Rogue INSTANCE = new Rogue();

	// Singleton pattern: private constructor
	private Rogue() {
		ImageMatrixGUI.setSize(WIDTH, HEIGHT + 1);
		ImageMatrixGUI.getInstance().registerObserver(this);
		ImageMatrixGUI.getInstance().setStatusMessage(
				"   level: " + level + "                                                        score: " + score
						+ "                                                     Highscore: " + highscore);

		scene = new BuilderRogueObjects("config/map.txt");

		staticInit();
	}

	// Singleton pattern inspector
	public static Rogue getInstance() {
		return INSTANCE;
	}

	private void staticInit() {// Cria hero e satusbar
		objectList = scene.getObjectList();
		enemyList = scene.getEnemyList();
		interactableList = scene.getInteractableList();

		hero = new Hero(new Point2D(1, 1));
		ImageMatrixGUI.getInstance().addImage(hero);
		for (int i = 0; i < 10; i++) {
			StatusBar status = new StatusBar(new Point2D(i, 10));
			if (i < 5)
				status.setGreen();
			objectList.add(status);
			ImageMatrixGUI.getInstance().addImage(status);
		}
	}

	// Starting-up GUI
	public void go() {
		ImageMatrixGUI.getInstance().go();
		ImageMatrixGUI.getInstance().update();
	}

	// Called everytime a key is pressed
	@Override
	public void update(Observed source) {
		int keyPressed = ImageMatrixGUI.getInstance().keyPressed();
		// System.out.println(keyPressed);
		if (!gameOver) {

			if (keyPressed == 37 || keyPressed == 38 || keyPressed == 39 || keyPressed == 40) {
				Direction d = Direction.directionFor(keyPressed);
				hero.move(d);
				enemyMove();
			}
			ImageMatrixGUI.getInstance().update();
		}
	}

	public RogueObject getHero() {
		return hero;
	}

	public void enemyMove() {
		for (MovingRogueObject o : enemyList) {
			o.move();
		}
	}

	public boolean isEnemy(Point2D nextP) {
		RogueObject aux;
		for (MovingRogueObject o : enemyList) {
			aux = (RogueObject) o;
			if (aux.getPosition().equals(nextP))
				return true;
		}
		return false;
	}

	public MovingRogueObject getEnemy(Point2D point) {
		RogueObject aux;
		for (MovingRogueObject o : enemyList) {
			aux = (RogueObject) o;
			if (aux.getPosition().equals(point))
				return o;
		}
		return enemyList.get(0);
	}

	public boolean canTake(Point2D point) {
		for (RogueObject o : objectList) {
			if (o.getPosition().equals(point))
				return true;
		}
		return false;
	}

	public void takeItem(Point2D point) {
		for (RogueObject o : objectList) {
			if (o.getPosition().equals(point) && o.getLayer() == 2)

				System.out.println("take");
		}
	}

	public void statusBarUpdate(int life) {
		Point2D aux = new Point2D((4 - (life / 2)), HEIGHT);
		for (RogueObject o : objectList) {
			if (o.getPosition().equals(aux)) {
				StatusBar auxStatus = (StatusBar) o;
				auxStatus.setRed();
			}
		}
		if (life == 0){
			System.out.println("Game Over");
			gameOver = true;
		}

	}

	public ArrayList<RogueObject> getObjectList() {
		return objectList;
	}

	public ArrayList<MovingRogueObject> getEnemyList() {
		return enemyList;
	}

	public ArrayList<InteractableRogueObject> getInteractableList() {
		return interactableList;
	}

	public void addObject(RogueObject obj) {
		System.out.println(obj.getName());
		objectList.add(obj);
	}

	public void addEnemy(MovingRogueObject enemy) {
		enemyList.add(enemy);
	}

	public void remove(RogueObject object) {
		objectList.remove(object);
		enemyList.remove(object);
		ImageMatrixGUI.getInstance().removeImage(object);
		ImageMatrixGUI.getInstance().update();
	}

	public boolean isDoor(Point2D point) {
		for (RogueObject obj : objectList) {
			if (obj.getPosition().equals(point)
					&& (obj.getName().equals("DoorClosed") || obj.getName().equals("DoorOpen")))
				return true;
		}
		return false;
	}

	public Door getDoor(Point2D nextP) {
		for (RogueObject obj : objectList) {
			if (obj.getPosition().equals(nextP)
					&& (obj.getName().equals("DoorClosed") || obj.getName().equals("DoorOpen")))
				return (Door) obj;
		}
		return null;
	}

	public void entry(Door door) {
		scene.buildRoom(door.getGoToRoom());

		staticInit();

	}

	public Point2D getDoorPosition(String goToDoor) {
		String[] aux = goToDoor.split(" ");
		int numberDoor = Integer.parseInt(aux[1]);
		Door door;
		for (RogueObject obj : objectList) {
			if ((obj.getName().equals("DoorClosed") || obj.getName().equals("DoorOpen"))) {
				door = (Door) obj;
				if (door.getNumber() == numberDoor) {
					return door.getPosition();
				}
			}
		}
		return null;
	}
}
