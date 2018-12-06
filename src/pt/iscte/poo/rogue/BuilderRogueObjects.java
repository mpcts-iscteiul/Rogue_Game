package pt.iscte.poo.rogue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pt.iscte.poo.rogue.objects.Bat;
import pt.iscte.poo.rogue.objects.Door;
import pt.iscte.poo.rogue.objects.Floor;
import pt.iscte.poo.rogue.objects.Hammer;
import pt.iscte.poo.rogue.objects.Key;
import pt.iscte.poo.rogue.objects.Skeleton;
import pt.iscte.poo.rogue.objects.Sword;
import pt.iscte.poo.rogue.objects.Thief;
import pt.iscte.poo.rogue.objects.Trap;
import pt.iscte.poo.rogue.objects.Wall;
import pt.iscte.poo.utils.InteractableRogueObject;
import pt.iscte.poo.utils.MovingRogueObject;
import pt.iscte.poo.utils.RogueObject;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class BuilderRogueObjects {

	String fileName;
	private ArrayList<RogueObject> objectList;
	private ArrayList<MovingRogueObject> enemyList;
	private ArrayList<InteractableRogueObject> interactableList;

	public BuilderRogueObjects(String fileName) {
		this.fileName = fileName;
		buildRoom("Room 1");
	}

	public void buildRoom(String room) {
		ImageMatrixGUI.getInstance().clearImages();
		objectList = new ArrayList<RogueObject>();
		enemyList = new ArrayList<MovingRogueObject>();
		interactableList = new ArrayList<InteractableRogueObject>();
		
		String line;
		String[] splitLine;
		boolean complete = false;
		try {
			Scanner scanner = new Scanner(new File(fileName));
			line = scanner.nextLine();

			while (!complete && scanner.hasNextLine()) {
				if (line.equals(room)) {
					while (scanner.hasNextLine()) {
						line = scanner.nextLine();
						splitLine = line.split(" ");
						if (splitLine[0].equals("Room"))
							break;
						else
							buildRogueObj(line);
					}
					complete = true;
				} else
					line = scanner.nextLine();
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro não encontrado...");
		}
	}

	private void buildRogueObj(String line) {
		String[] ln = line.split(" ");
		String obj = ln[0];
		switch (obj) {
		case "Floor":
			buildFloorWall(ln);
			break;
		case "Wall":
			buildFloorWall(ln);
			break;
		case "Bat":
			Bat bat = new Bat(new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2])));
			objectList.add(bat);
			enemyList.add(bat);
			ImageMatrixGUI.getInstance().addImage(bat);
			break;
		case ("Skeleton"):
			Skeleton skeleton = new Skeleton(new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2])));
			objectList.add(skeleton);
			enemyList.add(skeleton);
			ImageMatrixGUI.getInstance().addImage(skeleton);
			break;
		case ("Sword"):
			Sword sword = new Sword(new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2])));
			interactableList.add(sword);
			ImageMatrixGUI.getInstance().addImage(sword);
			break;
		case ("Trap"):
			Trap trap = new Trap(new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2])));
			objectList.add(trap);
			ImageMatrixGUI.getInstance().addImage(trap);
			break;
		case ("Hammer"):
			Hammer hammer = new Hammer(new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2])));
			interactableList.add(hammer);
			ImageMatrixGUI.getInstance().addImage(hammer);
			break;
		case ("Thief"):
			Thief thief = new Thief(new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2])));
			objectList.add(thief);
			enemyList.add(thief);
			ImageMatrixGUI.getInstance().addImage(thief);
			break;
		case ("Key"):
			Key key = new Key(new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2])));
			interactableList.add(key);
			ImageMatrixGUI.getInstance().addImage(key);
			break;
		case ("Door"):
			Point2D position = new Point2D(Integer.parseInt(ln[1]), Integer.parseInt(ln[2]));
			int number = Integer.parseInt(ln[3]);
			String room = ln[4]+" "+ln[5];
			String door = ln[6]+" "+ln[7];
			Door d = new Door(position, number, room, door);
			objectList.add(d);
			ImageMatrixGUI.getInstance().addImage(d);
			break;
		}

	}

	private void buildFloorWall(String[] line) {
		String[] infoX, infoY;
		int xi, xf, yi, yf;
		if (line[1].contains(":")) {
			infoX = line[1].split(":");
			xi = Integer.parseInt(infoX[0]);
			xf = Integer.parseInt(infoX[1]);
		} else {
			xi = Integer.parseInt(line[1]);
			xf = xi;
		}
		if (line[2].contains(":")) {
			infoY = line[2].split(":");
			yi = Integer.parseInt(infoY[0]);
			yf = Integer.parseInt(infoY[1]);
		} else {
			yi = Integer.parseInt(line[2]);
			yf = yi;
		}
		for (int x = xi; x <= xf; x++) {
			for (int y = yi; y <= yf; y++) {
				if (line[0].equals("Floor")) {
					ImageMatrixGUI.getInstance().addImage(new Floor(new Point2D(x, y)));
				}
				if (line[0].equals("Wall")) {
					Wall w = new Wall(new Point2D(x, y));
					objectList.add(w);
					ImageMatrixGUI.getInstance().addImage(w);
				}
			}
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
}
