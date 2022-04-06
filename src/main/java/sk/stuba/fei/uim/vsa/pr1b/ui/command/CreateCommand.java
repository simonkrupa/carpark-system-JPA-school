package sk.stuba.fei.uim.vsa.pr1b.ui.command;


import sk.stuba.fei.uim.vsa.pr1b.CarParkService;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;
import sk.stuba.fei.uim.vsa.pr1b.ui.*;


import java.util.List;
import java.util.stream.Collectors;

public class CreateCommand implements Command {

    private CarParkService carParkService;

    public CreateCommand() {
        this.carParkService = new CarParkService();
    }

    @Override
    public void execute(String wholeInput, List<String> parameters) {
        if(parameters.isEmpty()){
            System.out.println("Chýbajú parametre");
            return;
        }
        switch (parameters.get(0)) {
            case "carpark":
            case "carPark":
                parameters.remove(0);
                createCarPark();
                break;
            case "carparkfloor":
            case "carParkFloor":
                parameters.remove(0);
                createCarParkFloor();
                break;
            case "parkingspot":
            case "parkingSpot":
                parameters.remove(0);
                createParkingSpot();
                break;
            case "car":
                parameters.remove(0);
                createCar();
                break;
            case "carType":
            case "cartype":
                parameters.remove(0);
                createCarType();
                break;
            case "user":
                parameters.remove(0);
                createUser();
                break;
            case "reservation":
                parameters.remove(0);
                createReservation();
                break;
            default:
                printNoCommand(parameters);
                return;
        }
    }

    private Object createCarParkFloor(){
        String cpId = KeyboardInput.readString("Id carparku ").trim();
        String floorIdentifier = KeyboardInput.readString("Floor identifier ").trim();
        Long cpIdL = Long.parseLong(cpId);
        Object carParkFloor = carParkService.createCarParkFloor(cpIdL, floorIdentifier);
        if(carParkFloor==null){
            System.out.println("Chyba");
        }else {
            System.out.println(carParkFloor);
        }
        return carParkFloor;
    }

    private Object createParkingSpot(){
        String cpId = KeyboardInput.readString("Id carparku ").trim();
        String floorIdentifier = KeyboardInput.readString("Floor identifier ").trim();
        String spotIdentifier = KeyboardInput.readString("Spot identifier ").trim();
        Long cpIdL = Long.parseLong(cpId);
        Object parkingSpot = carParkService.createParkingSpot(cpIdL, floorIdentifier, spotIdentifier);
        if(parkingSpot==null){
            System.out.println("Chyba");
        }else {
            System.out.println(parkingSpot);
        }
        return parkingSpot;
    }

    private Object createUser(){
        String firstname = KeyboardInput.readString("Krstné meno ").trim();
        String lastname = KeyboardInput.readString("Priezvisko ").trim();
        String email = KeyboardInput.readString("Email ").trim();
        Object user = carParkService.createUser(firstname, lastname, email);
        if(user==null){
            System.out.println("Chyba");
        }else {
            System.out.println(user);
        }
        return user;

    }
    private Object createReservation(){
        String psId = KeyboardInput.readString("Id parkingspotu ").trim();
        Long psIdL = Long.parseLong(psId);
        String cId = KeyboardInput.readString("Id car ").trim();
        Long cIdL = Long.parseLong(cId);
        Object reservation = carParkService.createReservation(psIdL, cIdL);
        if(reservation==null){
            System.out.println("Chyba");
        }else {
            System.out.println(reservation);
        }
        return reservation;
    }

    private Object createCar(){
        String uId = KeyboardInput.readString("Id usera ").trim();
        Long uIdL = Long.parseLong(uId);
        String brand = KeyboardInput.readString("Značka ").trim();
        String model = KeyboardInput.readString("Model ").trim();
        String colour = KeyboardInput.readString("Farba ").trim();
        String vrp = KeyboardInput.readString("VRP ").trim();
        Object car = carParkService.createCar(uIdL, brand, model, colour, vrp);
        if(car==null){
            System.out.println("Chyba");
        }else {
            System.out.println(car);
        }
        return car;
    }

    private Object createCarType(){
        String name = KeyboardInput.readString("Názov ").trim();
        Object carType = carParkService.createCarType(name);
        if(carType==null){
            System.out.println("Chyba");
        }else {
            System.out.println(carType);
        }
        return carType;
    }


    private Object createCarPark() {
        String name = KeyboardInput.readString("Názov carparku ").trim();
        String address = KeyboardInput.readString("Adresa carparku ").trim();
        String pricePerHour = KeyboardInput.readString("Cena na hodinu ").trim();
        Integer pricePerHourL = Integer.parseInt(pricePerHour);
        Object carPark = carParkService.createCarPark(name, address, pricePerHourL);
        if(carPark==null){
            System.out.println("Chyba");
        }else {
            System.out.println(carPark);
        }
        return carPark;
    }

    private void printNoResult(List<String> params) {
        System.out.println("No result for parameters '" + String.join(" ", params) + "' were found.");
    }

    private void printNoCommand(List<String> params) {
        System.out.println("Unrecognised parameters! Parameters '" + String.join(" ", params) + "' has not been recognised!");
    }
}
