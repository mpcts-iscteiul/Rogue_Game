package pt.iscte.poo.rogue.test;

import static org.junit.Assert.*;

import java.awt.event.KeyEvent;

import org.junit.Before;
import org.junit.Test;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Vector2D;

public class DirectionTest {

	private Direction d; 
	
	@Before
	public void setUp() throws Exception {
		d = Direction.UP;
	}

	// example test
	@Test
	public void test() {
		assertEquals(Direction.UP, d);
		assertEquals(new Vector2D(0, -1), d.asVector());
		assertEquals("UP", d.name());
		assertEquals(Direction.UP, Direction.directionFor(KeyEvent.VK_UP));
	}

}
