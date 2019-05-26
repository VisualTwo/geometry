package de.technofiles.main;

import de.technofiles.model.Circle;
import de.technofiles.model.Coordinates;
import de.technofiles.model.Rectangle;
import de.technofiles.model.Triangle;

public class Geometry {

    public static void main(String[] args) {
	// Check the results by this online calculator:
	// https://rechneronline.de/pi/dreieck.php
	Triangle triangle = new Triangle(3.0, 4.0, 5.0);
	System.out.println(triangle.toString() + "\n");

	Triangle tri2 = new Triangle(new Coordinates(0.0, 0.0), new Coordinates(5.0, 0.0), new Coordinates(3.2, 2.4));
	System.out.println(tri2.toString() + "\n");

	Triangle tri3 = new Triangle(2.8284271247461903, 3.605551275463989, 5.0);
	System.out.println(tri3.toString() + "\n");

	Triangle tri4 = new Triangle(new Coordinates(0.0, 0.0), new Coordinates(5.0, 0.0), new Coordinates(3.0, 2.0));
	System.out.println(tri4.toString() + "\n");

	Rectangle rectangle = new Rectangle(3.0, 4.0);
	System.out.println(rectangle.toString() + "\n");

	Circle circle = new Circle(3.0);
	System.out.println(circle.toString());
    }
}
