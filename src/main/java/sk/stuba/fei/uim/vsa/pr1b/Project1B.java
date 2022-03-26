package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr1b.entities.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr1b.entities.User;

import java.util.List;
import java.util.Map;

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
        carParkService.createCarParkFloor(2L, "2");

        List<Object> c = carParkService.getCarParkFloors(1L);
        Object carParkFloor = carParkService.getCarParkFloor(3L);
        //Object carParkFloor2 = carParkService.deleteCarParkFloor(3L);
        Object parkingSpot = carParkService.createParkingSpot(2L, "1", "2");
        Object parkingSpot2 = carParkService.createParkingSpot(2L, "1", "3");
        Object cp = carParkService.getCarParkFloor(3L);
        Map<String, List<Object>> m = carParkService.getParkingSpots(2L);
        Object user = carParkService.createUser("Simon", "Krupa", "user@example.com");
        Object user1 = carParkService.getUser(7L);
        Object user2 = carParkService.getUser("user@example.com");
        List<Object> users = carParkService.getUsers();
        Object user3 = carParkService.deleteUser(8L);
        Object car = carParkService.createCar(7L, "skoda", "octavia", "biela", "XXX");
        Object car1 = carParkService.createCar(7L, "ford", "mondeo", "cierna", "YYY");
        user1 = carParkService.getUser(7L);
        List<Object> cars = carParkService.getCars(7L);
        Object deletedCar = carParkService.deleteCar(9L);
        List<Object> cars2 = carParkService.getCars(7L);
        System.out.println("aa");
    }

}
