package de.technofiles.model;

public class Coordinates {
    private double posX;
    private double posY;

    public Coordinates(double posX, double posY) {
	this.posX = posX;
	this.posY = posY;
    }

    /**
     * return information
     */
    @Override
    public String toString() {
	return "[" + Shape.round(posX, Shape.POSITIONS) + "|" + Shape.round(posY, Shape.POSITIONS) + "]";
    }

    public double getX() {
	return posX;
    }

    public double getY() {
	return posY;
    }

    public void setX(double posX) {
	this.posX = posX;
    }

    public void setY(double posY) {
	this.posY = posY;
    }

    /**
     * move coordinates
     * 
     * @param x
     * @param y
     */
    public void move(float x, float y) {
	moveX(x);
	moveY(y);
    }

    /**
     * move x coordinate
     * 
     * @param x
     */
    public void moveX(float x) {
	this.posX += x;
    }

    /**
     * move y coordinate
     * 
     * @param y
     */
    public void moveY(float y) {
	this.posY += y;
    }
}
