package baysensors;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void test() {
		
		Point p = new Point(0.0,0.0);
		assertEquals(0, p.x(), 0);
		assertEquals(0, p.y(), 0);
		assertEquals("(0.0,0.0)", p.toString());
		
		p = new Point(-1.0,-5.0);
		assertEquals(-1, p.x(), 0);
		assertEquals(-5, p.y(), 0);
		assertEquals("(-1.0,-5.0)", p.toString());
		
		p = new Point(-1.768,-5.98);
		assertEquals(-1.768, p.x(), 0);
		assertEquals(-5.98, p.y(), 0);
		assertEquals("(-1.768,-5.98)", p.toString());
		
	}
}
