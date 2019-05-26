package de.technofiles.model;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Shape {
    final static int POSITIONS = 6;

    ArrayList<Coordinates> point;
    Color colInside = Color.WHITE;
    Color colOutside = Color.BLACK;

    public Color getColInside() {
	return colInside;
    }

    public void setColInside(Color colInside) {
	this.colInside = colInside;
    }

    public Color getColOutside() {
	return colOutside;
    }

    public void setColOutside(Color colOutside) {
	this.colOutside = colOutside;
    }

    /**
     * @return the figure's circumference
     */
    abstract double getCircumference();

    /**
     * @return the figure's area
     */
    abstract double getArea();

    /**
     * help function to round a double to specified decimal places
     * 
     * @param input
     * @param positions
     * @return the rounded input
     */
    public static double round(double input, int positions) {
	if (input == 0.0 || positions < 1) {
	    return input;
	}
	int multiplier = (int) Math.pow(10, positions);
	return (double) Math.round(input * multiplier) / multiplier;
    }

    /**
     * distance between points a and b in a 2d coordinate system
     * 
     * @param a
     * @param b
     * @return the distance
     */
    public static double distance(Coordinates a, Coordinates b) {
	return Math.sqrt(Math.pow(b.getX() - a.getX(), 2) + (Math.pow(b.getY() - a.getY(), 2)));
    }

    /**
     * moving x and y from a source coordinate by a given length and an angle<br />
     * <br />
     * formulas:<br />
     * <ul>
     * <li>deviation-x = sin(angle) * length</li>
     * <li>deviation-y = cos(angle) * length</li>
     * 
     * @return coordinate resulting by a deviation
     */
    public static Coordinates deviation(Coordinates source, double length, double angle) {
	return new Coordinates(Math.sin(Math.toRadians(angle)) * length + source.getX(),
		Math.cos(Math.toRadians(angle)) * length + source.getY());
    }
}
