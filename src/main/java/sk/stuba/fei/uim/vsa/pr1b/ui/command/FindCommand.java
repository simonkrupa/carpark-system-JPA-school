package sk.stuba.fei.uim.vsa.pr1b.ui.command;

import sk.stuba.fei.uim.vsa.pr1b.CarParkService;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FindCommand implements Command {

    private CarParkService carParkService;

    public FindCommand() {
        this.carParkService = new CarParkService();
    }

    @Override
    public void execute(String wholeInput, List<String> parameters) {
        List<CarPark> carParks = new ArrayList<>();
        switch (parameters.get(0)) {
            case "carpark":
            case "carPark":
                parameters.remove(0);
                carParks = new ArrayList(findCarParks(parameters));
                break;
            default:
                printNoCommand(parameters);
                return;
        }
        if (carParks.isEmpty())
            printNoResult(parameters);
        else
            printGames(carParks);
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
        Object game = carParkService.getCarPark(params.get(0));
        return Collections.singletonList(game);
    }

    private List<Object> findCarParkById(List<String> params) {
        Long id = Long.parseLong(params.get(0));
        Object game = carParkService.getCarPark(id);
        return Collections.singletonList(game);
    }

    private void printNoResult(List<String> params) {
        System.out.println("No result for parameters '" + String.join(" ", params) + "' were found.");
    }

    private void printNoCommand(List<String> params) {
        System.out.println("Unrecognised parameters! Parameters '" + String.join(" ", params) + "' has not been recognised!");
    }

    private void printGames(List<CarPark> games) {
        games.forEach(game -> {
            System.out.println(game);
        });
    }

}
