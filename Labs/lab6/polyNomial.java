package lab6;

import java.lang.Math;
public class polyNomial {
    // ax^2+ bx + c
    private double a;
    private double b;
    private double c;
    public polyNomial(double initA ,double initB, double initC){
        this.a = initA;
        this.b = initB;
        this.c = initC;
    }

    public polyNomial(){
        this.a = 0.0;
        this.b = 0.0;
        this.c = 0.0;
    }

    public double getA(){return this.a;}
    public double getB(){return this.b;}
    public double getC(){return this.c;}

    public polyNomial add(polyNomial p){
        double newA = this.a + p.getA();
        double newB= this.b + p.getB();
        double newC = this.c + p.getC();
        polyNomial newPoly = new polyNomial(newA, newB, newC);
        return newPoly;
    }

    public double evaluate(double x){
        double part1 = this.a * (Math.pow(x, 2));
        double part2 = this.b * x;
        double finalNum = part1 + part2 + this.c;
        return finalNum;

    }

    public static void main(String[] args){
        polyNomial x = new polyNomial(5.0, -2.0, 3.0);
        System.out.println(x.evaluate(2.0));
    }
}
