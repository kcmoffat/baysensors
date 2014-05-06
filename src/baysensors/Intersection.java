package baysensors;
/**
 * 
 * @author kaseymoffat
 *	Wrapper class for various types of intersections between two lines
 */
public class Intersection {
	
	public static final int NONE = 0;
	public static final int POINT = 1;
	public static final int LINE = 2;
	
	private final Point pointIntersection;
	private final Line lineIntersection;
	private final int type;
	
	// Used for non-intersecting lines
	public Intersection () {
		this.pointIntersection = null;
		this.lineIntersection = null;
		this.type = NONE;
	}
	
	// Used for lines intersecting in a single point
	public Intersection (Point p) {
		this.pointIntersection = p;
		this.lineIntersection = null;
		this.type = POINT;
	}
	
	// Used for lines intersecting on a line
	public Intersection (Line l) {
		this.pointIntersection = null;
		this.lineIntersection = l;
		this.type = LINE;
	}
	
	public int type () {
		return this.type;
	}

	public String toString () {
		if (this.type == NONE) {
			return "No intersections";
		} else if (this.type == POINT) {
			return this.pointIntersection.toString();
		} else { // Line
			return this.lineIntersection.toString();
		}
	}
	
	/**
	 * Potential performance improvement - build hashCode and equals directly from
	 * instance variables, instead of using string representation
	 * (which involves allocating and initializing the string).
	 */
	public int hashCode () {
		return this.toString().hashCode();
	}
	
	public boolean equals (Object obj) {
		return this.toString().equals(obj.toString());
	}
}
