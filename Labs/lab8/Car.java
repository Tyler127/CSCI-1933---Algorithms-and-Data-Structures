public class Car extends Vehicle{
    private double mpg;
    public Car(){
        this.mpg = 30.0;
        super.nVehicles ++;
    }
    public Car(double mpg){
        this.mpg = mpg;
        super.nVehicles ++;
    }

    public double getMPG(){return this.mpg;}
    public void movingForward(){System.out.println("Car moving Forward");}
    public void movingBackward(){System.out.println("Car moving Backward");}

    public String toString(){
        return "Car : " + this.mpg;
    }
}
