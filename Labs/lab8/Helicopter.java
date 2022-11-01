public class Helicopter extends Vehicle{
    private double mpg;
    public Helicopter(){
        this.mpg = 0.3;
        super.nVehicles ++;
    }
    public Helicopter(double mpg){
        this.mpg = mpg;
        super.nVehicles ++;
    }

    public double getMPG(){return this.mpg;}
    public void movingForward(){System.out.println("Helicopter moving Forward");}
    public void movingBackward(){System.out.println("Helicopter moving Backward");}
    public void movingUp(){System.out.println("Helicopter mocing Up");}
    public void movingDown(){System.out.println("Helicopter moving Down");}

    public String toString(){
        return "Helicopter : " + this.mpg;
    }
}
