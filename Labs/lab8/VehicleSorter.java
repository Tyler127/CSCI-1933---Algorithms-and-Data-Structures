package lab8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class VehicleSorter {
	public static void main(String[] args) {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		//	e.g. vehicles.add(new Vehicle());
		Vehicle car = new Car();
		Vehicle betterCar = new Car(50.0);
		Vehicle train = new Train();
		Vehicle helicopter = new Helicopter();

		vehicles.add(car);
		vehicles.add(betterCar);
		vehicles.add(train);
		vehicles.add(helicopter);



		Collections.sort(vehicles);
		for (Vehicle v : vehicles) {
			System.out.println(v);
		}
	}
}
