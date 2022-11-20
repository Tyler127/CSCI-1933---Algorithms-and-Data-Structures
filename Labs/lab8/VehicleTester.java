package lab8;

public class VehicleTester {
    public static void main(String[] args){
        Car car = new Car(30.0);
        Car car2 = new Car(31.0);

        System.out.println(car.compareTo(car2));
    }
}
