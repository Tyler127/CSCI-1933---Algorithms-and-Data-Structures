package Project1;

import java.awt.Color;
import java.lang.Math;

public class Triangle {
    private double xPos = 0;
    private double yPos = 0;
    private double width = 0;
    private double height = 0;
    private Color triangleColor;

    public Triangle(double xPos, double yPos, double width, double height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
    // Calculators
    public double calculatePerimeter() {
        return width + (2 * (Math.sqrt( ((width/2)*(width/2)) + (height*height) )));
    }
    public double calculateArea() {
        return (width * height) / 2;
    }

    // Setters
    public void setColor(int red, int green, int blue) {
        triangleColor = new Color(red, green, blue);
    }
    public void setPos(double x, double y) {
        xPos = x;
        yPos = y;
    }
    public void setHeight(double newHeight) {
        height = newHeight;
    }
    public void setWidth(double newWidth) {
        width = newWidth;
    }

    // Getters
    public Color getColor() {
        return triangleColor;
    }
    public double getXPos() {
        return xPos;
    }
    public double getYPos() {
        return yPos;
    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    

    // public static void main(String[] args) {
    //     Triangle testTriangle = new Triangle(0,0,5,3);
    //     System.out.println(testTriangle.calculateArea());
    // }
}