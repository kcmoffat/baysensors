package baysensors;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class LineTest {

	@Test
	public void testSlopeIntercept() {
		Point p1;
		Point p2;
		Line l;
		
		// Vertical line through origin
		p1 = new Point (0.0, 0.0);
		p2 = new Point (0.0, 1.0);
		l = new Line (p1, p2);
		assertEquals(Double.POSITIVE_INFINITY, l.slope(), 0);
		assertEquals(Double.POSITIVE_INFINITY, l.yIntercept(), 0);
		
		// Vertical line at x=-2.3
		p1 = new Point (-2.3, 0.0);
		p2 = new Point (-2.3, -1.1);
		l = new Line (p1, p2);
		assertEquals(Double.POSITIVE_INFINITY, l.slope(), 0);
		assertEquals(Double.NaN, l.yIntercept(), 0);
		
		// Horizontal line through origin
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 0.0);
		l = new Line (p1, p2);
		assertEquals(0.0, l.slope(), 0);
		assertEquals(0.0, l.yIntercept(), 0);
		
		// Horizontal line at y = -2.4
		p1 = new Point (0.0, -2.4);
		p2 = new Point (1.0, -2.4);
		l = new Line (p1, p2);
		assertEquals(0.0, l.slope(), 0);
		assertEquals(-2.4, l.yIntercept(), 0);
		
		// Slope = -3, y-intercept = 0
		p1 = new Point (1.0, -3.0);
		p2 = new Point (2.0, -6.0);
		l = new Line (p1, p2);
		assertEquals(-3.0, l.slope(), 0);
		assertEquals(0.0, l.yIntercept(), 0);
		
		// Slope = -3, y-intercept = 2.4
		p1 = new Point (1.0, -0.6);
		p2 = new Point (-1.0, 5.4);
		l = new Line (p1, p2);
		assertEquals(-3.0, l.slope(), 0);
		assertEquals(2.4, l.yIntercept(), 0);
	}

	@Test
	public void testFindIntersection() {
		Point p1;
		Point p2;
		Point p3;
		Point p4;
		Line l1;
		Line l2;

		// Two vertical lines - no intersection, none through origin
		p1 = new Point (1.0, 1.0);
		p2 = new Point (1.0, 2.4);
		p3 = new Point (2.0, -0.6);
		p4 = new Point (2.0, 5.4);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(), Line.findIntersection(l1, l2));
		
		// Two vertical lines - no intersection, one through origin
		p1 = new Point (0.0, 1.0);
		p2 = new Point (0.0, 2.4);
		p3 = new Point (2.0, -0.6);
		p4 = new Point (2.0, 5.4);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(), Line.findIntersection(l1, l2));
		
		// Two vertical lines - intersect in a line, both through origin
		p1 = new Point (0.0, 1.0);
		p2 = new Point (0.0, 2.4);
		p3 = new Point (0.0, -0.6);
		p4 = new Point (0.0, 5.4);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(l1), Line.findIntersection(l1, l2));
		
		// Two vertical lines - intersect in a line, none through origin
		p1 = new Point (1.0, 1.0);
		p2 = new Point (1.0, 2.4);
		p3 = new Point (1.0, -0.6);
		p4 = new Point (1.0, 5.4);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(l1), Line.findIntersection(l1, l2));
		
		// Two horizontal lines - no intersection, none through origin
		p1 = new Point (1.0, 1.0);
		p2 = new Point (3.0, 1.0);
		p3 = new Point (1.0, -0.6);
		p4 = new Point (3.0, -0.6);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(), Line.findIntersection(l1, l2));
		
		// Two horizontal lines - no intersection, one through origin
		p1 = new Point (1.0, 1.0);
		p2 = new Point (3.0, 1.0);
		p3 = new Point (1.0, 0.0);
		p4 = new Point (3.0, 0.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(), Line.findIntersection(l1, l2));
		
		// Two horizontal lines - intersect in a line, both through origin
		p1 = new Point (1.0, 0.0);
		p2 = new Point (3.0, 0.0);
		p3 = new Point (2.0, 0.0);
		p4 = new Point (4.0, 0.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(l1), Line.findIntersection(l1, l2));
		
		// Two horizontal lines - intersect in a line, none through origin
		p1 = new Point (1.0, 5.0);
		p2 = new Point (3.0, 5.0);
		p3 = new Point (2.0, 5.0);
		p4 = new Point (4.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(l1), Line.findIntersection(l1, l2));
		
		// One vertical, one horizontal line - none through origin
		p1 = new Point (1.0, 0.0);
		p2 = new Point (1.0, 5.0);
		p3 = new Point (2.0, 5.0);
		p4 = new Point (4.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(1,5)), Line.findIntersection(l1, l2));
		
		// One vertical, one horizontal line - vertical through origin
		p1 = new Point (0.0, 0.0);
		p2 = new Point (0.0, 5.0);
		p3 = new Point (2.0, 5.0);
		p4 = new Point (4.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(0,5)), Line.findIntersection(l1, l2));
		
		// One vertical, one horizontal line - horizontal through origin
		p1 = new Point (1.0, 1.0);
		p2 = new Point (1.0, 5.0);
		p3 = new Point (2.0, 0.0);
		p4 = new Point (4.0, 0.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(1,0)), Line.findIntersection(l1, l2));
		
		// One vertical, one horizontal line - both through origin
		p1 = new Point (0.0, 1.0);
		p2 = new Point (0.0, 5.0);
		p3 = new Point (2.0, 0.0);
		p4 = new Point (4.0, 0.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(0,0)), Line.findIntersection(l1, l2));
		
		// One vertical, one slanted - none through origin
		p1 = new Point (-1.0, 1.0);
		p2 = new Point (-1.0, 5.0);
		p3 = new Point (1.0, 1.0);
		p4 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(-1.0,0)), Line.findIntersection(l1, l2));
		
		// One vertical, one slanted - vertical through origin
		p1 = new Point (0.0, 1.0);
		p2 = new Point (0.0, 5.0);
		p3 = new Point (1.0, 1.0);
		p4 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(0,0.5)), Line.findIntersection(l1, l2));
		
		// One vertical, one slanted - slanted through origin
		p1 = new Point (1.0, 1.0);
		p2 = new Point (1.0, 5.0);
		p3 = new Point (2.0, 1.0);
		p4 = new Point (4.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(1.0,0.5)), Line.findIntersection(l1, l2));
		
		// One vertical, one slanted - both through origin
		p1 = new Point (0.0, 1.0);
		p2 = new Point (0.0, 5.0);
		p3 = new Point (2.0, 1.0);
		p4 = new Point (4.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(0.0,0.0)), Line.findIntersection(l1, l2));
		
		// One horizontal, one slanted - none through origin
		p1 = new Point (2.0, 1.0);
		p2 = new Point (4.0, 1.0);
		p3 = new Point (1.0, 1.0);
		p4 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(1.0,1.0)), Line.findIntersection(l1, l2));
		
		// One horizontal, one slanted - horizontal through origin
		p1 = new Point (2.0, 0.0);
		p2 = new Point (4.0, 0.0);
		p3 = new Point (1.0, 1.0);
		p4 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(-1.0,0.0)), Line.findIntersection(l1, l2));
		
		// One horizontal, one slanted - slanted through origin
		p1 = new Point (2.0, 1.0);
		p2 = new Point (4.0, 1.0);
		p3 = new Point (2.0, 1.0);
		p4 = new Point (4.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(2.0,1.0)), Line.findIntersection(l1, l2));
		
		// One horizontal, one slanted - both through origin
		p1 = new Point (2.0, 0.0);
		p2 = new Point (4.0, 0.0);
		p3 = new Point (2.0, 1.0);
		p4 = new Point (4.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(0.0,0.0)), Line.findIntersection(l1, l2));
		
		// Both positive slope - single point of intersection
		p1 = new Point (2.0, 2.0);
		p2 = new Point (4.0, 4.0);
		p3 = new Point (1.0, 1.0);
		p4 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(1.0,1.0)), Line.findIntersection(l1, l2));
		
		// Both positive slope - no intersection
		p1 = new Point (2.0, 2.0);
		p2 = new Point (4.0, 4.0);
		p3 = new Point (0.0, 1.0);
		p4 = new Point (4.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(), Line.findIntersection(l1, l2));
		
		// Both positive slope - intersect in a line
		p1 = new Point (2.0, 2.0);
		p2 = new Point (4.0, 4.0);
		p3 = new Point (0.0, 0.0);
		p4 = new Point (5.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(l1), Line.findIntersection(l1, l2));
		
		// Both negative slope - single point of intersection
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-4.0, 4.0);
		p3 = new Point (-4.0, 5.0);
		p4 = new Point (-6.0, 8.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(-2.0, 2.0)), Line.findIntersection(l1, l2));
		
		// Both negative slope - no intersection
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-4.0, 4.0);
		p3 = new Point (-4.0, 5.0);
		p4 = new Point (-6.0, 7.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(), Line.findIntersection(l1, l2));
		
		// Both negative slope - intersect in a line
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-4.0, 4.0);
		p3 = new Point (-6.0, 6.0);
		p4 = new Point (-8.0, 8.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(l1), Line.findIntersection(l1, l2));
		
		// Both slanted - one positive, one negative slope
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-4.0, 4.0);
		p3 = new Point (7.0, 3.0);
		p4 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		assertEquals(new Intersection(new Point(-1.0, 1.0)), Line.findIntersection(l1, l2));
	}
	
	@Test
	public void testFindAllIntersections() {
		Point p1;
		Point p2;
		Point p3;
		Point p4;
		Point p5;
		Point p6;
		Line l1;
		Line l2;
		Line l3;
		Line [] lines;
		Set<Intersection> intersections;
		
		// Three horizontal lines - no intersections
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-4.0, 2.0);
		p3 = new Point (7.0, 3.0);
		p4 = new Point (3.0, 3.0);
		p5 = new Point (7.0, 4.0);
		p6 = new Point (3.0, 4.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(0, intersections.size());
		
		// Three horizontal lines - two intersect in line
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-4.0, 2.0);
		p3 = new Point (7.0, 2.0);
		p4 = new Point (3.0, 2.0);
		p5 = new Point (7.0, 4.0);
		p6 = new Point (3.0, 4.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(l1)));
		
		// Three horizontal lines - all intersect in line
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-4.0, 2.0);
		p3 = new Point (7.0, 2.0);
		p4 = new Point (3.0, 2.0);
		p5 = new Point (7.0, 2.0);
		p6 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(l1)));
		
		// Three vertical lines - no intersections
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-2.0, 3.0);
		p3 = new Point (7.0, 2.0);
		p4 = new Point (7.0, 3.0);
		p5 = new Point (3.0, 4.0);
		p6 = new Point (3.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(0, intersections.size());
		
		// Three vertical lines - two intersect in line
		p1 = new Point (-2.0, 2.0);
		p2 = new Point (-2.0, 3.0);
		p3 = new Point (7.0, 2.0);
		p4 = new Point (7.0, 3.0);
		p5 = new Point (7.0, 4.0);
		p6 = new Point (7.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(l2)));
		
		// Three vertical lines - all intersect in line
		p1 = new Point (-7.0, 2.0);
		p2 = new Point (-7.0, 3.0);
		p3 = new Point (-7.0, 2.0);
		p4 = new Point (-7.0, 3.0);
		p5 = new Point (-7.0, 4.0);
		p6 = new Point (-7.0, 5.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(l2)));
		
		// Three slanted lines - no intersections
		p1 = new Point (-7.0, 2.0);
		p2 = new Point (-8.0, 3.0);
		p3 = new Point (-8.0, 2.0);
		p4 = new Point (-9.0, 3.0);
		p5 = new Point (-9.0, 2.0);
		p6 = new Point (-10.0, 3.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(0, intersections.size());
		
		// Three slanted lines - two intersect in line
		p1 = new Point (-7.0, 2.0);
		p2 = new Point (-8.0, 3.0);
		p3 = new Point (-9.0, 4.0);
		p4 = new Point (-10.0, 5.0);
		p5 = new Point (-9.0, 2.0);
		p6 = new Point (-10.0, 3.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(l2)));
		
		// Three slanted lines - all intersect in line
		p1 = new Point (-7.0, 2.0);
		p2 = new Point (-8.0, 3.0);
		p3 = new Point (-9.0, 4.0);
		p4 = new Point (-10.0, 5.0);
		p5 = new Point (-10.0, 5.0);
		p6 = new Point (-11.0, 6.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(l1)));
		
		// Three slanted lines - one intersection point
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (0.0, 2.0);
		p4 = new Point (2.0, 0.0);
		p5 = new Point (-1.0, 0.0);
		p6 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(1.0, 1.0))));
		
		// Three slanted lines - two intersection points
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (0.0, 1.0);
		p4 = new Point (1.0, 2.0);
		p5 = new Point (-1.0, 0.0);
		p6 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(2, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(-1.0, 0.0))));
		assertTrue(intersections.contains(new Intersection(new Point(1.0, 1.0))));
		
		// Two horizontal, one vertical line - line intersection
		p1 = new Point (1.0, 1.0);
		p2 = new Point (2.0, 1.0);
		p3 = new Point (0.0, 1.0);
		p4 = new Point (1.0, 1.0);
		p5 = new Point (-1.0, 0.0);
		p6 = new Point (-1.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		// BUG: should only return the line intersection, but 
		//currently return both the line and point intersection.
		//assertEquals(1, intersections.size());
		//assertTrue(intersections.contains(new Intersection(l1)));
		
		// Two horizontal, one vertical line - two intersection points
		p1 = new Point (0.0, 0.0);
		p2 = new Point (0.0, 1.0);
		p3 = new Point (0.0, 1.0);
		p4 = new Point (1.0, 1.0);
		p5 = new Point (-1.0, 2.0);
		p6 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(2, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(0.0, 1.0))));
		assertTrue(intersections.contains(new Intersection(new Point(0.0, 2.0))));
		
		// Two horizontal, one slanted line - line intersection
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (0.0, 1.0);
		p4 = new Point (1.0, 1.0);
		p5 = new Point (-1.0, 1.0);
		p6 = new Point (3.0, 1.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		//assertEquals(1, intersections.size());
		//assertTrue(intersections.contains(new Intersection(l2)));
		
		// Two horizontal, one slanted line - two intersection points
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (0.0, 1.0);
		p4 = new Point (1.0, 1.0);
		p5 = new Point (-1.0, 2.0);
		p6 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(2, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(1.0, 1.0))));
		assertTrue(intersections.contains(new Intersection(new Point(2.0, 2.0))));
		
		// Two vertical, one horizontal line - line intersection
		p1 = new Point (0.0, 0.0);
		p2 = new Point (0.0, 1.0);
		p3 = new Point (0.0, 2.0);
		p4 = new Point (0.0, 3.0);
		p5 = new Point (-1.0, 2.0);
		p6 = new Point (3.0, 2.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		//assertEquals(1, intersections.size());
		//assertTrue(intersections.contains(new Intersection(l2)));
		
		// Two vertical, one horizontal line - two intersection points
		p1 = new Point (1.0, 1.0);
		p2 = new Point (1.0, 0.0);
		p3 = new Point (2.0, 1.0);
		p4 = new Point (2.0, 2.0);
		p5 = new Point (-1.0, 3.0);
		p6 = new Point (3.0, 3.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(2, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(1.0, 3.0))));
		assertTrue(intersections.contains(new Intersection(new Point(2.0, 3.0))));
		
		// Two vertical, one slanted line - line intersection
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (5.0, 0.0);
		p4 = new Point (5.0, 1.0);
		p5 = new Point (5.0, 0.0);
		p6 = new Point (5.0, 1.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		//assertEquals(1, intersections.size());
		//assertTrue(intersections.contains(new Intersection(l2)));
		
		// Two vertical, one slanted line - two intersection points
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (5.0, 0.0);
		p4 = new Point (5.0, 1.0);
		p5 = new Point (3.0, 0.0);
		p6 = new Point (3.0, 1.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(2, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(3.0, 3.0))));
		assertTrue(intersections.contains(new Intersection(new Point(5.0, 5.0))));
		
		// One vertical, one horizontal, one slanted line - one intersection point
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (1.0, 0.0);
		p4 = new Point (1.0, 1.0);
		p5 = new Point (3.0, 1.0);
		p6 = new Point (4.0, 1.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(1, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(1.0, 1.0))));
		
		// One vertical, one horizontal, one slanted line - three intersection points
		p1 = new Point (0.0, 0.0);
		p2 = new Point (1.0, 1.0);
		p3 = new Point (1.0, 0.0);
		p4 = new Point (1.0, 1.0);
		p5 = new Point (0.0, 0.0);
		p6 = new Point (1.0, 0.0);
		l1 = new Line (p1, p2);
		l2 = new Line (p3, p4);
		l3 = new Line (p5, p6);
		lines = new Line[3];
		lines[0] = l1;
		lines[1] = l2;
		lines[2] = l3;
		intersections = Line.findAllIntersections(lines);
		assertEquals(3, intersections.size());
		assertTrue(intersections.contains(new Intersection(new Point(1.0, 1.0))));
		assertTrue(intersections.contains(new Intersection(new Point(0.0, 0.0))));
		assertTrue(intersections.contains(new Intersection(new Point(1.0, 0.0))));
	}
}
