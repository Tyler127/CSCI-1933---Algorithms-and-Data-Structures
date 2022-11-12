package Projects.Project1;

import java.awt.Color;

public class Rectangle {
    private double XPos = 0;
    private double YPos = 0;
    private double width = 0;
    private double height = 0;
    private Color rectangleColor;

    public Rectangle(double xPos, double yPos, double width, double height) {
        XPos = xPos;
        YPos = yPos;
        this.width = width;
        this.height = height;
    }

    // Calculators
    public double calculatePerimeter() {
        return (width*2) + (height*2);
    }
    public double calculateArea() {
        return width * height;
    }

    // Setters
    public void setXPos(double xPos) {
        XPos = xPos;
    }
    public void setYPos(double yPos) {
        YPos = yPos;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setColor(int red, int green, int blue) {
        rectangleColor = new Color(red, green, blue);
    }

    // Getters
    public double getXPos() {
        return XPos;
    }
    public double getYPos() {
        return YPos;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public Color getColor() {
        return rectangleColor;
    }
}