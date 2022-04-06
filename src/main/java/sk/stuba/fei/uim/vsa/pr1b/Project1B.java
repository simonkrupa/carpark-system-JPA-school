package sk.stuba.fei.uim.vsa.pr1b;

import sk.stuba.fei.uim.vsa.pr1b.entities.*;
import sk.stuba.fei.uim.vsa.pr1b.ui.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Project1B {

    public static void main(String[] args) throws InterruptedException {
//        CarParkService carParkService = new CarParkService();
        Repl repl = new Repl();
        repl.start();
////
//        Object car = carParkService.getCar(25L);
//        if(car instanceof Car){
//            ((Car) car).setVehicleRegistrationPlate("PK481JK");        //TT335BN");
//        }
//        Object ps = carParkService.updateCar(car);
//        Object r1 = carParkService.createReservation(17L, 23L);
//        Object r2 = carParkService.createReservation(18L, 24L);
//        Object r3 = carParkService.createReservation(19L, 25L);
//        Object r4 = carParkService.createReservation(20L, 26L);
//        Object r5 = carParkService.createReservation(21L, 27L);
//        Object r6 = carParkService.createReservation(22L, 28L);

//        Object er1 = carParkService.endReservation(51L);
//        Object er2 = carParkService.endReservation(53L);
//        Object er3 = carParkService.endReservation(54L);
//        Object er4 = carParkService.endReservation(56L);


//        Object cp1 = carParkService.createCarPark("Ikea parkovisko", "Ivanská cesta 16, 82104, Bratislava", 2);
//        Object cp2 = carParkService.createCarPark("Aupark", "Veľká Okružna 59A, Žilina", 1);
//        Object cp3 = carParkService.createCarPark("ShopBox", "Herlianska 60, 040 14 Košice", 3);
//
//        Object u1 = carParkService.createUser("Jožko", "Mrkvička", "jozko@mrkvicka.sk");
//        Object u2 = carParkService.createUser("Ferko", "Mrkvička", "ferko666@gmail.com");
//        Object u3 = carParkService.createUser("Zuzana", "Mrkvičková", "mrkvickova25@hotmail.com");
//        Object u4 = carParkService.createUser("Jana", "Bystrá", "jana.bystra@gmail.com");
//        Object u5 = carParkService.createUser("Tomáš", "Kováč", "tominok38@gmail.com");
//        Object u6 = carParkService.createUser("Michaela", "Žitná", "zitna.miska@yahoo.com");
//
//        Object cp1cpf1 = carParkService.createCarParkFloor(1L, "P1");
//        Object cp1cpf2 = carParkService.createCarParkFloor(1L, "P2");
//        Object cp1cpf3 = carParkService.createCarParkFloor(1L, "P3");
//
//        Object cp2cpf1 = carParkService.createCarParkFloor(2L, "-1");
//        Object cp2cpf2 = carParkService.createCarParkFloor(2L, "-2");
//
//        Object cp3cpf1 = carParkService.createCarParkFloor(3L, "A");
//
//        Object cp1cpf2ps1 = carParkService.createParkingSpot(1L, "P2", "2022");
//        Object cp1cpf1ps1 = carParkService.createParkingSpot(1L, "P1", "1002");
//        Object cp1cpf3ps1 = carParkService.createParkingSpot(1L, "P3", "3055");
//
//        Object cp2cpf1ps1 = carParkService.createParkingSpot(2L, "-1", "1025");
//        Object cp2cpf2ps1 = carParkService.createParkingSpot(2L, "-2", "2123");
//
//        Object cp3cpf1ps1 = carParkService.createParkingSpot(3L, "A", "3568");
//
//        Object u1c1 = carParkService.createCar(4L, "Audi", "A7", "modrá", "BL345XF");
//        Object u2c1 = carParkService.createCar(5L, "Renault", "Clio", "zelená", "PK481JK");
//        Object u3c1 = carParkService.createCar(6L, "BMW", "X5", "šedá", "TT335BN");
//        Object u4c1 = carParkService.createCar(7L, "Kia", "Ceed", "tmavomodrá", "NM421ZU");
//        Object u5c1 = carParkService.createCar(8L, "Hyundai", "i30", "oranžová", "ZA698GH");
//        Object u6c1 = carParkService.createCar(9L, "Mercedes", "GLA", "červená", "KE725SD");


//        //od zaciatku
//        Object carPark1 = carParkService.createCarPark("VB","skolska",5);
//        Object carPark2 = carParkService.createCarPark("AB","ave",4);
////        if(carPark2 instanceof CarPark){
////            ((CarPark) carPark2).setAddress("adr");
////        }
//
//        Object carPark3 = carParkService.getCarPark(3L);
//        Object carPark4 = carParkService.getCarPark("VB");
//
//
////        carParkService.updateCarPark(carPark2);
////        Object carPark5 = carParkService.deleteCarPark(2L);
//        Object carParkFloor = carParkService.createCarParkFloor(1L, "algo");
//        Object carParkFloor2 = carParkService.createCarParkFloor(1L, "ago");
//        Object carParkFloor3 = carParkService.createCarParkFloor(2L, "ba");
//        Object carParkFloor4 = carParkService.createCarParkFloor(2L, "algo");
////        Object carPark5 = carParkService.deleteCarPark(2L);
//        List<Object> pf = carParkService.getCarParkFloors(2L);
//        Object delFloor = carParkService.deleteCarParkFloor(4L);
//
//        if (carParkFloor3 instanceof CarParkFloor){
//            ((CarParkFloor) carParkFloor3).setFloorIdentifier("cc");
//        }
//        Object updatedFloor = carParkService.updateCarParkFloor(carParkFloor3);
//
//        Object parkingSpot = carParkService.createParkingSpot(1L, "algo", "bueno");
////        Object delType = carParkService.deleteCarType(7L);
////        Object ddddd= carParkService.deleteParkingSpot(8L);
////        Object del32Type = carParkService.deleteCarType(7L);
//        Object parkingSpot2 = carParkService.createParkingSpot(1L, "algo", "bu");
//        Object parkingSpot3 = carParkService.createParkingSpot(1L, "algo", "be");
//        Object parkingSpot4 = carParkService.createParkingSpot(1L, "algo", "bc");
//        Object parkingSpot5 = carParkService.createParkingSpot(1L, "algo", "eee");
//        Object parkingSpot6 = carParkService.createParkingSpot(1L, "algo", "nn");
//        Object parkingSpot7 = carParkService.createParkingSpot(1L, "algo", "ou");
//        Object parkingSpot8 = carParkService.createParkingSpot(2L, "cc", "nn");
//        Object parkingSpot9 = carParkService.createParkingSpot(2L, "cc", "uu");
//        Object parkingSpot10 = carParkService.createParkingSpot(2L, "algo", "uu");
//        //Object delfloor = carParkService.deleteCarParkFloor(3L);
//        Object delspot = carParkService.deleteParkingSpot(8L);
//
//        List<Object> pss = carParkService.getParkingSpots(1L, "algo");
//        Object parkingSpot11 = carParkService.deleteParkingSpot(7L);
////        Map<String, List<Object>> map = carParkService.getParkingSpots(2L);
//
//        Object user = carParkService.createUser("Simon", "Krupa", "hoco@email.com");
//        Object user1 = carParkService.createUser("Jozef", "Kaka", "kaka@email.com");
//
////        Object deletedUser = carParkService.deleteUser(9L);
//        Object car = carParkService.createCar(17L, "ford", "mondeo", "black", "XZY");
//        Object car2 = carParkService.createCar(18L, "A", "B", "C", "XZ");
//        Object car3 = carParkService.createCar(17L, "ford", "fiesta", "x", "PPP");
//        Object car4 = carParkService.createCar(18L, "skoda", "octavia", "biela", "LLL");
////        List<Object> users = carParkService.getUsers();
////        Object car3 = carParkService.getCar(11L);
////        Object car4 = carParkService.getCar("XZY");
////        List<Object> cars = carParkService.getCars(10L);
////        Object delCar = carParkService.deleteCar(11L);
////       Object reservation = carParkService.createReservation(13L, 20L);
////        Object reservation2 = carParkService.createReservation(9L, 17L);
////        Object reservation3 = carParkService.createReservation(10L, 18L);
//
////       Object ps= carParkService.getParkingSpot(13L);
////        List<Object> ress = carParkService.getReservations(8L, new Date());
////        List<Object> resss = carParkService.getMyReservations(10L);
//
//        Map<String, List<Object>> mapAvailSpots = carParkService.getAvailableParkingSpots("VB");
//        Map<String, List<Object>> map2 = carParkService.getAvailableParkingSpots("AB");
//        Map<String, List<Object>> mapAvailSpots3 = carParkService.getOccupiedParkingSpots("VB");
//        Map<String, List<Object>> mapAvailSpots4 = carParkService.getOccupiedParkingSpots("AB");
////        Object deleteFloor = carParkService.deleteCarParkFloor(5L);
////        Object deleteSpot = carParkService.deleteParkingSpot(8L);
////        Object delcarpark = carParkService.deleteCarPark(1L);
////        Object delCar = carParkService.deleteCar(16L);
////        Object delUser = carParkService.deleteUser(14L);
//        List<Object> listCarParks = carParkService.getCarParks();
//
//
//        Object carType2 = carParkService.createCarType("diesel");
//        Object car10 = carParkService.createCar(17L, "skoda", "fabia", "red", "RRR", 23L);
//        List<Object> listCarTypes = carParkService.getCarTypes();
//        Object carType3 = carParkService.getCarType("diesel");
//        Object carType4 = carParkService.getCarType("diese");
//        Object carType5 = carParkService.getCarType(23L);
//        Object parkingSpot12 = carParkService.createParkingSpot(2L, "cc", "xd", 23L);
//        Object reservation = carParkService.createReservation(13L, 20L);
////        TimeUnit.SECONDS.sleep(5);
////        Object endRes = carParkService.endReservation(26L);
//        Object res2 = carParkService.createReservation(13L, 21L);
////        Object  delCarr = carParkService.deleteCar(20L);
////        Object delTYpe = carParkService.deleteCarType(23L);
//        Object ddddddd = carParkService.deleteParkingSpot(13L);
////        Object ddcar = carParkService.deleteCar(24L);
////        Object del2TYpe = carParkService.deleteCarType(23L);
////        Object ddspot = carParkService.deleteParkingSpot(25L);
////        Object del3TYpe = carParkService.deleteCarType(23L);
//////        Object delUser = carParkService.deleteUser(17L);
////        Object delUser2 = carParkService.deleteUser(18L);
//       // Object delCarPark = carParkService.deleteCarPark(1L);
//
//        System.out.println("aaa");


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
