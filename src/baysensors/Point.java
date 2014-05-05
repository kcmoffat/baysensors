package baysensors;
/**
 * Wrapper class for a two-dimensional R^2 point
 * @author kaseymoffat
 *
 */
public class Point {
	
	private final double x;
	private final double y;
	
	public Point(double x, double y) {
		if (x == -0.0) {
			this.x = 0.0;
		} else {
			this.x = x;
		}
		if (y == -0.0) {
			this.y = 0.0;
		} else {
			this.y = y;
		}
	}
	
	public double x() {
		return this.x;
	}
	
	public double y() {
		return this.y;
	}
	
	public String toString () {
		return "(" + this.x + "," + this.y + ")";
	}
	
	public boolean equals (Object obj) {
		return this.toString().equals(obj.toString());
	}
}
