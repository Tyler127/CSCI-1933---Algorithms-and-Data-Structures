package Projects.Project1;

import java.awt.Color;

public class Circle {
    private double xPos = 0;
    private double yPos = 0;
    private double radius = 0;
    private Color circleColor;
    
    public Circle(double xPos, double yPos, double radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    // Calculators
    public double calculatePerimeter() {
        return 3.141592 * radius * 2;
    }
    public double calculateArea() {
        return 3.141592 * (radius*radius);
    }

    // Setters
    public void setColor(int red, int green, int blue) {
        circleColor = new Color(red, green, blue);
    }
    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }
    public void setRadius(double newradius) {
        radius = newradius;
    }

    // Getters
    public Color getColor() {
        return circleColor;
    }
    public double getXPos() {
        return xPos;
    }
    public double getYPos() {
        return yPos;
    }
    public double getRadius() {
        return radius;
    }
}