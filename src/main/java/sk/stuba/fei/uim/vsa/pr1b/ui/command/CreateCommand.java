package sk.stuba.fei.uim.vsa.pr1b.ui.command;

import sk.stuba.fei.uim.vsa.pr1b.CarParkService;
import sk.stuba.fei.uim.vsa.pr1b.entities.CarPark;

import java.util.List;
import java.util.stream.Collectors;

public class CreateCommand implements Command {

    private CarParkService carParkService;

    public CreateCommand() {
        this.carParkService = new CarParkService();
    }

    @Override
    public void execute(String wholeInput, List<String> parameters) {
        switch (parameters.get(0)) {
            case "game":
                parameters.remove(0);
                createGame(parameters);
                break;
            default:
                printNoCommand(parameters);
                return;
        }
    }

    private Object createGame(List<String> params) {
        String name = params.get(0);
        params.remove(0);
        Object carPark = carParkService.createCarPark("daco", "daka", 6);
        System.out.println(carPark);
        return carPark;
    }

    private void printNoResult(List<String> params) {
        System.out.println("No result for parameters '" + String.join(" ", params) + "' were found.");
    }

    private void printNoCommand(List<String> params) {
        System.out.println("Unrecognised parameters! Parameters '" + String.join(" ", params) + "' has not been recognised!");
    }
}
