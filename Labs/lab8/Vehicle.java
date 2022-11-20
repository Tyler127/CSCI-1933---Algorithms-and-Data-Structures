package lab8;

public abstract class Vehicle implements Comparable<Vehicle> {
    public abstract void movingForward();
    public abstract void movingBackward();
    public abstract double getMPG();

    public void start(){System.out.println("Starting Vehicle Engine..");}
    public void stop(){System.out.println("Stopping Vehicle Engine..");}
    protected int nVehicles = 0;

    /**
     * this method returns true if and only if o is the same type
     * of vehicle and has the same mpg as the vehicle being called upon
     */
    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            Vehicle v = (Vehicle)o;
            if (getMPG() == v.getMPG()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // public static final int getNumVehicles() {
    //     return nVehicles;
    // }

    // This method is used for sorting vehicles by their MPG.
    public int compareTo(Vehicle v) {
        int x;
        if (this.getMPG() < v.getMPG()){
            x = -1;
        }else if(this.getMPG() > v.getMPG()){
            x = 1;
        }else{
            x = 0;
        }
         return x;
    }
}
