package baysensors;
import java.util.Set;
import java.util.HashSet;

/**
 * Wrapper class for a line in R^2.  A line is defined by two points.
 * @author kaseymoffat
 *
 */
public class Line {

		private final Point p1;
		private final Point p2;

	public Line (Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Point p1() {
		return this.p1;
	}

	public Point p2() {
		return this.p2;
	}
	
	public double slope () {
		if ((p2.x() - p1.x()) == 0) {
			return Double.POSITIVE_INFINITY;
		} else {
			return (p2.y() - p1.y()) / (p2.x() - p1.x());
		}
	}
	
	public double yIntercept () {
		if (p1.x() == p2.x()) {
			if (p1.x() == 0) {
				return Double.POSITIVE_INFINITY;
			} else {
				return Double.NaN;
			}
		} else {
			return p1.y() - p1.x() * this.slope();
		}
	}
	
	public static Intersection findIntersection(Line l1, Line l2) {
		if (l1.slope() == l2.slope()) {
			if (l1.yIntercept() == l2.yIntercept()) {
				return new Intersection(l1);
			} else if (new Double(l1.yIntercept()).equals(Double.NaN) && new Double(l2.yIntercept()).equals(Double.NaN)) {
				if (l1.p1().x() == l2.p1().x()) {
					return new Intersection (l1);
				} else {
					return new Intersection();
				}
			} else {
				return new Intersection();
			}
		} else if (l1.slope() == Double.POSITIVE_INFINITY) {
			double x = l1.p1().x();
			double y = l2.slope() * x + l2.yIntercept();
			return new Intersection (new Point (x, y));
		} else if (l2.slope() == Double.POSITIVE_INFINITY) {
			double x = l2.p1().x();
			double y = l1.slope() * x + l1.yIntercept();
			return new Intersection (new Point (x, y));
		} else {
			double x = (l2.yIntercept() - l1.yIntercept()) / (l1.slope() - l2.slope());
			double y = l1.slope() * x + l1.yIntercept();
			return new Intersection (new Point (x, y));
		}
	}
	
	public static Set<Intersection> findAllIntersections (Line [] lines) {
		Set<Intersection> intersections = new HashSet<Intersection> ();
		for (int i = 0; i < lines.length; i++) {
			for (int j = i+1; j < lines.length; j++) {
				Intersection intn = findIntersection(lines[i], lines[j]);
				if (!intersections.contains(intn)) {
					if (intn.type() != Intersection.NONE) {
						System.out.println(intn);
						intersections.add(intn);
					}
				}
			}
		}
		return intersections;
	}
	
	public String toString () {
		if (this.slope() == Double.POSITIVE_INFINITY) {
			return "x=" + this.p1().x();
		} else {
			String slopeClean = "";
			String yInterceptClean = "";
			String operator = "";
			if (this.slope() != 0.0) {
				slopeClean = this.slope() + "x";
			}
			if (this.yIntercept() != 0.0) {
				yInterceptClean = this.yIntercept() + "";
			}
			if (this.yIntercept() > 0.0 && this.slope() != 0.0) {
				operator = "+";
			}
			if (this.slope() == 0.0 && this.yIntercept() == 0.0) {
				yInterceptClean = 0.0 + "";
			}
			return "y=" + slopeClean + operator + yInterceptClean;
		}
	}
	
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}
}
