package sk.stuba.fei.uim.vsa.pr1b.ui.command;

import java.util.List;

public class HelpCommand implements Command {

    public static final String HELP = "Rezervačný systém parkovacích domov.\n" +
            "Táto aplikácia slúži na starostlivosť o chod parkovacích domov.\n" +
            "Pre prácu s aplikáciou využite nasledovné príkazy:\n" +
            "   exit, quit, q   - Ukončenie programu\n" +
            "   help, ?         - Výpis tohto textu pomoci\n" +
            "---------------------------------------------------\n" +
            "   find            - Výpis hľadaného objektu\n" +
            "   parametre pre find   - carpark [byid {id carparku}, byname {meno carparku}, all]\n" +
            "                        - carparkfloor [byid {id carparkfloor}, all {id carparku}]\n" +
            "                        - parkingspot [byid {id parkingspot}, allbyfloor {id carpark, flooridentifier}]\n" +
            "                        - user [byid {id usera}, byemail {email usera}, all]\n" +
            "                        - car [byid {id car}, byvrp {VRP car}, all {id usera}]\n" +
            "                        - reservation [byid {id parkingspotu, date <format: dd/mm/yyyy>}, allbyuser {id usera}]\n" +
            "                        - cartype []\n" +
            "   príklad: find carpark byid 2\n" +
            "---------------------------------------------------\n" +
            "   create          - Vytvorenie nového objektu\n" +
            "   parametre pre create - carpark\n" +
            "                        - carparkfloor\n" +
            "                        - parkingspot\n" +
            "                        - user\n" +
            "                        - car\n" +
            "                        - reservation\n" +
            "                        - cartype\n" +
            "---------------------------------------------------\n" +
            "   delete          - Vymazanie objektu / ukončenie rezervácie \n" +
            "   parametre pre delete - carpark\n" +
            "                        - carparkfloor\n" +
            "                        - parkingspot\n" +
            "                        - user\n" +
            "                        - car\n" +
            "                        - reservation\n" +
            "                        - cartype\n" +
            "---------------------------------------------------\n";


    @Override
    public void execute(String wholeInput, List<String> parameters) {
        System.out.println(HELP);
    }
}
