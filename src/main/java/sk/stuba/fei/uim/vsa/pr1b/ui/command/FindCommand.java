package sk.stuba.fei.uim.vsa.pr1b.ui.command;

import sk.stuba.fei.uim.vsa.pr1b.CarParkService;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FindCommand implements Command {

    private CarParkService carParkService;

    public FindCommand() {
        this.carParkService = new CarParkService();
    }

    @Override
    public void execute(String wholeInput, List<String> parameters) {
        List<Object> container = new ArrayList<>();
        switch (parameters.get(0)) {
            case "carpark":
            case "carPark":
                parameters.remove(0);
                container = new ArrayList(findCarParks(parameters));
                break;
            case "carparkfloor":
            case "carParkFloor":
                parameters.remove(0);
                container = new ArrayList(findCarParkFloors(parameters));
                break;
            case "parkingspot":
            case "parkingSpot":
                parameters.remove(0);
                container = new ArrayList(findParkingSpots(parameters));
                break;
            case "user":
                parameters.remove(0);
                container = new ArrayList(findUsers(parameters));
                break;
            case "car":
                parameters.remove(0);
                container = new ArrayList(findCars(parameters));
                break;
            case "reservation":
                parameters.remove(0);
                container = new ArrayList(findReservations(parameters));
                break;
            case "carType":
            case "cartype":
                parameters.remove(0);
                container = new ArrayList(findCarTypes(parameters));
                break;
            default:
                printNoCommand(parameters);
                return;
        }
        if (container.isEmpty())
            printNoResult(parameters);
        else
            printContainer(container);
    }

    private List<Object> findCarTypes(List<String> params) {
        switch (params.get(0)) {
//            case "byId":
//            case "byid":
//                params.remove(0);
//                return findReservationByIdAndDate(params);
//            case "allByUser":
//                params.remove(0);
//                return findReservationsByUser(params);
            default:
                printNoCommand(params);
                return new ArrayList<>();
        }
    }

    private List<Object> findReservations(List<String> params) {
        switch (params.get(0)) {
            case "byId":
            case "byid":
                params.remove(0);
                return findReservationByIdAndDate(params);
            case "allByUser":
            case "allbyuser":
                params.remove(0);
                return findReservationsByUser(params);
            default:
                printNoCommand(params);
                return new ArrayList<>();
        }
    }

    private List<Object> findReservationByIdAndDate(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        params.remove(0);
        String d = params.get(0);
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(d);
            List<Object> reservations = carParkService.getReservations(id, date);
            return reservations;
        }catch (Exception e) {
//            return null;
            return new ArrayList<>();
        }
    }

    private List<Object> findReservationsByUser(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        List<Object> reservations = carParkService.getMyReservations(id);
        return reservations;
    }

    private List<Object> findCars(List<String> params) {
        switch (params.get(0)) {
            case "byId":
            case "byid":
                params.remove(0);
                return findCarById(params);
            case "byvrp":
            case "byVRP":
                params.remove(0);
                return findCarByVRP(params);
            case "all":
                params.remove(0);
                return findCarsByUser(params);
            default:
                printNoCommand(params);
                return new ArrayList<>();
        }
    }

    private List<Object> findCarById(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        Object car = carParkService.getCar(id);
        return Collections.singletonList(car);
    }

    private List<Object> findCarByVRP(List<String> params) {
        Object car = carParkService.getCar(params.get(0));
        return Collections.singletonList(car);
    }

    private List<Object> findCarsByUser(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        List<Object> cars = carParkService.getCars(id);
        return cars;
    }

    private List<Object> findUsers(List<String> params) {
        switch (params.get(0)) {
            case "byId":
            case "byid":
                params.remove(0);
                return findUserById(params);
            case "byemail":
            case "byEmail":
                params.remove(0);
                return findUserByEmail(params);
            case "all":
                return carParkService.getUsers();
            default:
                printNoCommand(params);
                return new ArrayList<>();
        }
    }

    private List<Object> findUserByEmail(List<String> params) {
        Object user = carParkService.getUser(params.get(0));
        return Collections.singletonList(user);
    }

    private List<Object> findUserById(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        Object user = carParkService.getUser(id);
        return Collections.singletonList(user);
    }

    private List<Object> findParkingSpots(List<String> params) {
        switch (params.get(0)) {
            case "byId":
            case "byid":
                params.remove(0);
                return findParkingSpotById(params);
            case "allbyfloor":
            case "allByFloor":
                params.remove(0);
                return findAllParkingSpotsByFloor(params);
//            case "all":
//                params.remove(0);
//                return findAllParkingSpots(params);
            default:
                printNoCommand(params);
                return new ArrayList<>();
        }
    }

    private Map<String, List<Object>> findAllParkingSpots(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        Map<String, List<Object>> parkingSpots = carParkService.getParkingSpots(id);
        return parkingSpots;
    }

    private List<Object> findAllParkingSpotsByFloor(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        params.remove(0);
        String floorIdentifier = params.get(0);
        List<Object> parkingSpots = carParkService.getParkingSpots(id, floorIdentifier);
        return parkingSpots;
    }

    private List<Object> findParkingSpotById(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        Object parkingSpot = carParkService.getParkingSpot(id);
        return Collections.singletonList(parkingSpot);
    }

    private List<Object> findCarParkFloors(List<String> params){
        switch (params.get(0)) {
            case "byId":
            case "byid":
                params.remove(0);
                return findCarParkFloorById(params);
            case "all":
                params.remove(0);
                return findCarParkAllFloors(params);
            default:
                printNoCommand(params);
                return new ArrayList<>();
        }
    }

    private List<Object> findCarParkAllFloors(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        List<Object> carParkFloors = carParkService.getCarParkFloors(id);
        return carParkFloors;
    }

    private List<Object> findCarParkFloorById(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        Object carParkFloor = carParkService.getCarParkFloor(id);
        return Collections.singletonList(carParkFloor);
    }

    private List<Object> findCarParks(List<String> params) {
        switch (params.get(0)) {
            case "byId":
            case "byid":
                params.remove(0);
                return findCarParkById(params);
            case "byname":
            case "byName":
                params.remove(0);
                return findCarParkByName(params);
            case "all":
                return carParkService.getCarParks();
            default:
                printNoCommand(params);
                return new ArrayList<>();
        }
    }

    private List<Object> findCarParkByName(List<String> params) {
        Object carPark = carParkService.getCarPark(params.get(0));
        return Collections.singletonList(carPark);
    }

    private List<Object> findCarParkById(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        Object carPark = carParkService.getCarPark(id);
        return Collections.singletonList(carPark);
    }

    private void printNoResult(List<String> params) {
        System.out.println("No result for parameters '" + String.join(" ", params) + "' were found.");
    }

    private void printNoCommand(List<String> params) {
        System.out.println("Unrecognised parameters! Parameters '" + String.join(" ", params) + "' has not been recognised!");
    }

    private void printContainer(List<Object> container) {
        container.forEach(System.out::println);
    }

}
