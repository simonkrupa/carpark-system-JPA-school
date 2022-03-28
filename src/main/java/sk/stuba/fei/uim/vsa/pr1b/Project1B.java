package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarParkFloor;
import sk.stuba.fei.uim.vsa.pr1b.entities.ParkingSpot;
import sk.stuba.fei.uim.vsa.pr1b.entities.User;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Project1B {

    public static void main(String[] args) throws InterruptedException {
        CarParkService carParkService = new CarParkService();

        //od zaciatku
        Object carPark1 = carParkService.createCarPark("VB","skolska",5);
        Object carPark2 = carParkService.createCarPark("AB","ave",4);
//        if(carPark2 instanceof CarPark){
//            ((CarPark) carPark2).setAddress("adr");
//        }

        Object carPark3 = carParkService.getCarPark(3L);
        Object carPark4 = carParkService.getCarPark("VB");


//        carParkService.updateCarPark(carPark2);
//        Object carPark5 = carParkService.deleteCarPark(2L);
        Object carParkFloor = carParkService.createCarParkFloor(1L, "algo");
        Object carParkFloor2 = carParkService.createCarParkFloor(1L, "ago");
        Object carParkFloor3 = carParkService.createCarParkFloor(2L, "ba");
        Object carParkFloor4 = carParkService.createCarParkFloor(2L, "algo");
//        Object carPark5 = carParkService.deleteCarPark(2L);
        List<Object> pf = carParkService.getCarParkFloors(2L);
        Object delFloor = carParkService.deleteCarParkFloor(4L);

        if (carParkFloor3 instanceof CarParkFloor){
            ((CarParkFloor) carParkFloor3).setFloorIdentifier("cc");
        }
        Object updatedFloor = carParkService.updateCarParkFloor(carParkFloor3);

        Object parkingSpot = carParkService.createParkingSpot(1L, "algo", "bueno");
        Object parkingSpot2 = carParkService.createParkingSpot(1L, "algo", "bu");

        List<Object> pss = carParkService.getParkingSpots(3L, "algo");
        Object parkingSpot3 = carParkService.deleteParkingSpot(7L);
//        Map<String, List<Object>> map = carParkService.getParkingSpots(2L);
        List<Object> listCarParks = carParkService.getCarParks();
        Object user = carParkService.createUser("Simon", "Krupa", "hoco@email.com");
        Object user1 = carParkService.createUser("Simon", "Krupa", "hoo@email.com");
        Object u = carParkService.getUser("hoco@email.com");
//        Object deletedUser = carParkService.deleteUser(9L);
        Object car = carParkService.createCar(9L, "ford", "mondeo", "black", "XZY");
        Object car2 = carParkService.createCar(10L, "A", "B", "C", "XZ");
        List<Object> users = carParkService.getUsers();
        Object car3 = carParkService.getCar(11L);
        Object car4 = carParkService.getCar("XZY");
        List<Object> cars = carParkService.getCars(10L);
        Object delCar = carParkService.deleteCar(11L);
        System.out.println("aaa");


//        carParkService.createCarPark("dubw", "ala", 5);
//        carParkService.createCarPark("fef", "ae", 3);
//        Object carPark = carParkService.getCarPark(2L);
//        Object carPark2 = carParkService.getCarPark("duw");
//        List<Object> carParks = carParkService.getCarParks();
////        CarPark cp = new CarPark("ax", "vb", 6);
////        carParkService.updateCarPark(cp);
//        carParkService.deleteCarPark(3L);
//        carParkService.createCarParkFloor(2L, "1");
//        carParkService.createCarParkFloor(2L, "2");
//
//        List<Object> c = carParkService.getCarParkFloors(1L);
//        Object carParkFloor = carParkService.getCarParkFloor(3L);
//        //Object carParkFloor2 = carParkService.deleteCarParkFloor(3L);
//        Object parkingSpot = carParkService.createParkingSpot(2L, "1", "2");
//        Object parkingSpot2 = carParkService.createParkingSpot(2L, "1", "3");
//        Object cp = carParkService.getCarParkFloor(3L);
//        Map<String, List<Object>> m = carParkService.getParkingSpots(2L);
//        Object user = carParkService.createUser("Simon", "Krupa", "user@example.com");
//        Object user1 = carParkService.getUser(7L);
//        Object user2 = carParkService.getUser("user@example.com");
//        List<Object> users = carParkService.getUsers();
//        Object user3 = carParkService.deleteUser(8L);
//        Object car = carParkService.createCar(7L, "skoda", "octavia", "biela", "XXX");
//        Object car1 = carParkService.createCar(7L, "ford", "mondeo", "cierna", "YYY");
//        user1 = carParkService.getUser(7L);
//        List<Object> cars = carParkService.getCars(7L);
//        Object deletedCar = carParkService.deleteCar(9L);
//        List<Object> cars2 = carParkService.getCars(7L);
//        Object res = carParkService.createReservation(5L, 8L);
//       // TimeUnit.SECONDS.sleep(5);
//        Object parkingSpot3 = carParkService.getParkingSpot(5L);
//        carParkService.endReservation(10L);
//        List<Object> reservations = carParkService.getReservations(5L, new Date());
//        System.out.println("aa");

    }

}
