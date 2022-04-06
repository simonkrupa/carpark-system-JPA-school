package sk.stuba.fei.uim.vsa.pr1b.ui.command;

import sk.stuba.fei.uim.vsa.pr1b.CarParkService;
import sk.stuba.fei.uim.vsa.pr1b.ui.*;

import java.util.List;

public class DeleteCommand implements Command{

    private CarParkService carParkService;

    public DeleteCommand() {
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
                deleteCarPark();
                break;
            case "carparkfloor":
            case "carParkFloor":
                parameters.remove(0);
                deleteCarParkFloor();
                break;
            case "parkingspot":
            case "parkingSpot":
                parameters.remove(0);
                deleteParkingSpot();
                break;
            case "car":
                parameters.remove(0);
                deleteCar();
                break;
            case "carType":
            case "cartype":
                parameters.remove(0);
                deleteCarType();
                break;
            case "user":
                parameters.remove(0);
                deleteUser();
                break;
            case "reservation":
                parameters.remove(0);
                deleteReservation();
                break;
            default:
                printNoCommand(parameters);
                return;
        }
    }

    private Object deleteCar(){
        String Id = KeyboardInput.readString("Car Id ").trim();
        Long IdL = Long.parseLong(Id);
        Object deletedObject = carParkService.deleteCar(IdL);
        if(deletedObject==null){
            System.out.println("Chyba");
        }else {
            System.out.println(deletedObject);
        }
        return deletedObject;
    }

    private Object deleteCarPark(){
        String Id = KeyboardInput.readString("CarPark Id ").trim();
        Long IdL = Long.parseLong(Id);
        Object deletedObject = carParkService.deleteCarPark(IdL);
        if(deletedObject==null){
            System.out.println("Chyba");
        }else {
            System.out.println(deletedObject);
        }
        return deletedObject;
    }

    private Object deleteCarParkFloor(){
        String Id = KeyboardInput.readString("CarParkFloor Id ").trim();
        Long IdL = Long.parseLong(Id);
        Object deletedObject = carParkService.deleteCarParkFloor(IdL);
        if(deletedObject==null){
            System.out.println("Chyba");
        }else {
            System.out.println(deletedObject);
        }
        return deletedObject;
    }

    private Object deleteParkingSpot(){
        String Id = KeyboardInput.readString("ParkingSpot Id ").trim();
        Long IdL = Long.parseLong(Id);
        Object deletedObject = carParkService.deleteParkingSpot(IdL);
        if(deletedObject==null){
            System.out.println("Chyba");
        }else {
            System.out.println(deletedObject);
        }
        return deletedObject;
    }

    private Object deleteUser(){
        String Id = KeyboardInput.readString("User Id ").trim();
        Long IdL = Long.parseLong(Id);
        Object deletedObject = carParkService.deleteUser(IdL);
        if(deletedObject==null){
            System.out.println("Chyba");
        }else {
            System.out.println(deletedObject);
        }
        return deletedObject;
    }

    private Object deleteReservation(){
        String Id = KeyboardInput.readString("Reservation Id ").trim();
        Long IdL = Long.parseLong(Id);
        Object deletedObject = carParkService.endReservation(IdL);
        if(deletedObject==null){
            System.out.println("Chyba");
        }else {
            System.out.println(deletedObject);
        }
        return deletedObject;
    }

    private Object deleteCarType(){
        String Id = KeyboardInput.readString("CarType Id ").trim();
        Long IdL = Long.parseLong(Id);
        Object deletedObject = carParkService.deleteCarType(IdL);
        if(deletedObject==null){
            System.out.println("Chyba");
        }else {
            System.out.println(deletedObject);
        }
        return deletedObject;
    }

    private void printNoResult(List<String> params) {
        System.out.println("No result for parameters '" + String.join(" ", params) + "' were found.");
    }

    private void printNoCommand(List<String> params) {
        System.out.println("Unrecognised parameters! Parameters '" + String.join(" ", params) + "' has not been recognised!");
    }
}
