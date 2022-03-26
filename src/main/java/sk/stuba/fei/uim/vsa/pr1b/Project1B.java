package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr1b.entities.ParkingSpot;

import java.util.List;

public class Project1B {

    public static void main(String[] args) {
        CarParkService carParkService = new CarParkService();
        carParkService.createCarPark("dubw", "ala", 5);
        carParkService.createCarPark("fef", "ae", 3);
        Object carPark = carParkService.getCarPark(2L);
        Object carPark2 = carParkService.getCarPark("duw");
        List<Object> carParks = carParkService.getCarParks();
//        CarPark cp = new CarPark("ax", "vb", 6);
//        carParkService.updateCarPark(cp);
        carParkService.deleteCarPark(3L);
        carParkService.createCarParkFloor(2L, "1");
        List<Object> c = carParkService.getCarParkFloors(1L);
        Object carParkFloor = carParkService.getCarParkFloor(3L);
        //Object carParkFloor2 = carParkService.deleteCarParkFloor(3L);
        Object parkingSpot = carParkService.createParkingSpot(2L, "1", "2");
        Object cp = carParkService.getCarParkFloor(3L);
        System.out.println("aa");
    }

}
