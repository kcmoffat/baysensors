package baysensors;

import static org.junit.Assert.*;

import org.junit.Test;

public class IntersectionTest {

	@Test
	public void testTypeToString() {
		// No intersection
		Intersection i = new Intersection();
		assertEquals(Intersection.NONE, i.type());
		assertEquals("No intersections", i.toString());
		
		// Point intersection
		Point p = new Point (-1.3,0.4);
		i = new Intersection(p);
		assertEquals(Intersection.POINT, i.type());
		assertEquals("(-1.3,0.4)", i.toString());
		
		// Line intersection
		Point p1 = new Point (0.0, 0.0);
		Point p2 = new Point (1.0, 1.0);
		Line l = new Line (p1, p2);
		i = new Intersection(l);
		assertEquals(Intersection.LINE, i.type());
		assertEquals("y=1.0x", i.toString());
	}
	
	@Test
	public void testEquals() {
		// Two Points - not equal
		// Two Points - equal
		// Two Lines - not equal
		// Two Lines - equal
		// Point and Line
		// Point and vertical Line
		// Point and no intersection
		// Line and no intersection
	}
}
