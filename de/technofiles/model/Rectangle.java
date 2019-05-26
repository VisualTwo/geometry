package de.technofiles.model;

public class Rectangle extends Shape {
    Coordinates coordA;
    Coordinates coordB;
    Coordinates coordC;
    Coordinates coordD;
    double a = 0.0;
    double b = 0.0;

    // Construct if you have lengths of all sides
    public Rectangle(double a, double b) {
	this.a = a;
	this.b = b;
	this.coordA = new Coordinates(0.0, 0.0);
	this.coordB = new Coordinates(a, 0.0);
	this.coordC = new Coordinates(a, b);
	this.coordD = new Coordinates(0.0, b);
    }

    public Rectangle(Coordinates a, Coordinates b, Coordinates c) {
	this.coordA = a;
	this.coordB = b;
	this.coordC = c;
	this.coordD = new Coordinates(a.getX(), c.getY());
	this.a = distance(coordA, coordB);
	this.b = distance(coordB, coordC);
    }

    /**
     * return information
     */
    @Override
    public String toString() {
	return ("Rectangle with a: " + round(this.getA(), POSITIONS) + " b: " + round(this.getB(), POSITIONS)
		+ "\nArea: " + round(this.getArea(), POSITIONS) + "\nCircumference: "
		+ round(this.getCircumference(), POSITIONS) + "\nA at " + this.coordA.toString() + "\nB at "
		+ this.coordB.toString() + "\nC at " + this.coordC.toString() + "\nD at " + this.coordD.toString());
    }

    public double getA() {
	return a;
    }

    public double getB() {
	return b;
    }

    /**
     * @return true if the rectangle has side length a==b
     */
    public boolean isSquare() {
	// is a square if lengths equal each other
	return a == b;
    }

    /**
     * @return the circumference size
     */
    @Override
    public double getCircumference() {
	// if one side
	if (a == 0.0 || b == 0.0) {
	    return 0.0;
	}
	return 2 * a + 2 * b;
    }

    /**
     * @return the area size
     */
    @Override
    public double getArea() {
	return a * b;
    }
}
