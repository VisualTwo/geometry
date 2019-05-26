package de.technofiles.model;

public class Circle extends Shape {
    private double radius;
    private Coordinates middle = null;

    public Circle(double radius) {
	this.radius = radius;
    }

    public Circle(Coordinates middle, double radius) {
	this.setMiddle(middle);
	this.radius = radius;
    }

    public Coordinates getMiddle() {
	return middle;
    }

    public void setMiddle(Coordinates middle) {
	this.middle = middle;
    }

    /**
     * return information
     */
    @Override
    public String toString() {
	return "Circle with radius " + radius + "\nDiameter: " + getDiameter() + "\nCircumference: "
		+ getCircumference() + "\nArea: " + getArea();
    }

    /**
     * @return the diameter (German: Durchmesser) of the circle
     */
    public double getDiameter() {
	return radius * 2;
    }

    /**
     * @return the circumference size
     */
    @Override
    public double getCircumference() {
	if (radius == 0.0) {
	    return 0.0;
	}
	return 2 * Math.PI * radius;
    }

    /**
     * @return the area size
     */
    @Override
    public double getArea() {
	if (radius == 0.0) {
	    return 0.0;
	}
	return Math.PI * radius * radius;
    }
}
