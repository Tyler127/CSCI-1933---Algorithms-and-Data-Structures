// FractalDrawer class draws a fractal of a shape indicated by user input
import java.awt.Color;
import java.util.Scanner;
import java.lang.Math;

public class FractalDrawer {
    private double totalArea=0;  // member variable for tracking the total area
    public Canvas theCanvas; // Initialize the canvas object

    public FractalDrawer() {}  // contructor

    // drawFractal creates a new Canvas object
    // and determines which shapes to draw a fractal by calling appropriate helper function
    // drawFractal returns the area of the fractal
    public double drawFractal(String type) {
        Canvas theCanvas = new Canvas(1600, 1600);

        if (type.equals("triangle")) {
            System.out.println("making triangle");
            drawTriangleFractal(200, 200, 400, 400, theCanvas, 7);
            System.out.println(totalArea);
            return totalArea;
        }
        else if (type.equals("circle")) {
            drawCircleFractal(100, 400, 400, theCanvas, 7); 
        }
        else if (type.equals("rectangle")) {
            drawRectangleFractal(75, 150, 400, 400, theCanvas, 7);
        }
        return 0.0;
    }

    // drawTriangleFractal draws a triangle fractal using recursive techniques
    public void drawTriangleFractal(double width, double height, double x, double y, Canvas can, int level){
        // RGB Values
        // Idea 3 used here
        int red = 40 + (int)(Math.random() * 120);
        int blue = 30 + (int)(Math.random() * 100);
        int green = 30 + (int)(Math.random() * 100);

        // Making and Drawing triangle
        Triangle theTriangle = new Triangle(x, y, width, height);
        theTriangle.setColor(red, green, blue);
        can.drawShape(theTriangle);

        // Area addition
        totalArea += theTriangle.calculateArea();

        // Recursion to make 3 more triangles from parent until level = 0
        if (level == 0) {
            return;
        }
        else {
            // Idea 2 used here
            drawTriangleFractal(width/2, height/2, x+width, y, can, level-1); // Right side of parent triangle
            drawTriangleFractal(width/2, height/2, x-(width/2), y, can, level-1); // Left side of parent triangle
            drawTriangleFractal(width/2, height/2, x+(width/4), y-height, can, level-1); // Top point of parent triangle
        }
    }

    // drawCircleFractal draws a circle fractal using recursive techniques
    public void drawCircleFractal(double radius, double x, double y, Canvas can, int level) {
        // RGB Values
        // Idea 3 used here
        int red = 40 + (int)(Math.random() * 120);
        int blue = 30 + (int)(Math.random() * 100);
        int green = 30 + (int)(Math.random() * 100);

        Circle theCircle = new Circle(x, y, radius);
        theCircle.setColor(red, green, blue);
        can.drawShape(theCircle);

         // Recursion to make 4 more circles from parent until level = 0
         if (level == 0) {
            return;
        }
        else {
            // Idea 2 used here
            drawCircleFractal(radius/2, x, y - (radius + (radius/2)),can,level - 1);//top
            drawCircleFractal(radius/2, x, y + (radius + (radius/2)),can,level - 1);//bottom
            drawCircleFractal(radius/2, x - (radius + (radius/2)), y,can,level - 1);//left
            drawCircleFractal(radius/2, x + (radius + (radius/2)), y,can,level - 1);//right
        }
    }

    // drawRectangleFractal draws a rectangle fractal using recursive techniques
    public void drawRectangleFractal(double width, double height, double x, double y, Canvas can, int level) {
        // RGB Values
        // Idea 3 used here
        int red = 40 + (int)(Math.random() * 120);
        int blue = 30 + (int)(Math.random() * 100);
        int green = 30 + (int)(Math.random() * 100);


        Rectangle theRectangle = new Rectangle(x, y, width, height);
        theRectangle.setColor(red,green,blue);
        can.drawShape(theRectangle);

        // Recursion to make 4 more rectangles from parent until level = 0
        if (level == 0) {
            return;
        }
        else {
            // Idea 2 used here
            drawRectangleFractal(width/2, height/2, x - (width/2),  y - (height/2), can, level-1);//upper left
            drawRectangleFractal(width/2, height/2, x - (width/2),  y + (height), can, level-1);//lower left
            drawRectangleFractal(width/2, height/2, x + width,  y - height/2, can, level-1);//upper right
            drawRectangleFractal(width/2, height/2, x + width,  y + height, can, level-1);//lower right
        }
    }

    public static void main(String[] args){
        Scanner scanna = new Scanner(System.in);
        FractalDrawer drawer = new FractalDrawer();

        System.out.println("Type of shape:");
        String shapetype = scanna.nextLine();

        System.out.println("shapetype: " + shapetype);
        drawer.drawFractal(shapetype);
    
        scanna.close();
    }
}
