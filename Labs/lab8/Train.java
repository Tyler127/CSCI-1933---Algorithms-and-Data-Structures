public class Train extends Vehicle{
    private double mpg;
    public Train(){
        this.mpg = 470.0;
        super.nVehicles ++;
    }
    public Train(double mpg){
        this.mpg = mpg;
        super.nVehicles ++;
    }

    public double getMPG(){return this.mpg;}
    public void movingForward(){System.out.println("Train moving Forward");}
    public void movingBackward(){System.out.println("Train moving Backward");}
    public void enteringStation(){System.out.println("Train entering Station");}
    public void leavingStation(){System.out.println("Train leaving Station");}

    public String toString(){
        return "Train : " + this.mpg;
    }
}
