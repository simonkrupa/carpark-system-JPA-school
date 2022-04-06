package sk.stuba.fei.uim.vsa.pr1b.ui;

import sk.stuba.fei.uim.vsa.pr1b.ui.command.*;

import java.util.*;

public class Repl {

    private final Map<String, Command> commands;

    public Repl() {
        this.commands = new HashMap<>();
        commands.put("print", new PrintCommand());
        commands.put("help", new HelpCommand());
        commands.put("?", new HelpCommand());
        commands.put("find", new FindCommand());
        commands.put("create", new CreateCommand());
        commands.put("delete", new DeleteCommand());
    }

    public void start() {
        System.out.println("Rezervačný systém parkovacích domov.\n" +
                "Táto aplikácia slúži na starostlivosť o chod parkovacích domov.\n" +
                "Pre prácu s aplikáciou využite nasledovné príkazy:\n" +
                "   exit, quit, q   - Ukončenie programu\n" +
                "   help, ?         - Výpis tohto textu pomoci\n" +
                "   find            - Výpis hľadaného elementu\n" +
                "   parametre pre find - carpark [byid {id carparku}, byname {meno carparku}, all]\n" +
                "                      - carparkfloor [byid {id carparkfloor}, all {id carparku}]\n" +
                "                      - parkingspot [byid {id parkingspot}, allbyfloor {id carpark, flooridentifier}]\n" +
                "                      - user [byid {id usera}, byemail {email usera}, all]\n" +
                "                      - car [byid {id car}, byvrp {VRP car}, all {id usera}]\n" +
                "                      - reservation [byid {id parkingspotu, date <format: dd/mm/yyyy>}, allbyuser {id usera}]\n" +
                "                      - cartype []\n" +
                "   príklad: find carpark byid 2\n");
        while (true) {
            String input = KeyboardInput.readString("").trim();
            switch (input) {
                case "q":
                case "exit":
                case "quit":
                    return;
            }
            if (isCommand(input)) {
                executeCommand(input);
            } else {
                System.out.println("Input '" + input + "' was not recognised as a known command!");
                executeCommand("help");
            }
        }
    }

    private String getCommand(String input) {
        String command = "";
        if (!input.contains(" ")) {
            command = input.trim();
        } else {
            command = input.substring(0, input.indexOf(' '));
        }
        return command;
    }

    private boolean isCommand(String input) {
        return commands.containsKey(getCommand(input));
    }

    private void executeCommand(String input) {
        if (!isCommand(input)) return;
        String command = getCommand(input);
        List<String> parameters = new ArrayList<>(Arrays.asList(input.split(" ")));
        parameters.remove(0);
        commands.get(command).execute(input, parameters);
    }
}
