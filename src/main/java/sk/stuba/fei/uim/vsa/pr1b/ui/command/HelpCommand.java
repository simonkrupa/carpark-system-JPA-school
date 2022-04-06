package sk.stuba.fei.uim.vsa.pr1b.ui.command;

import java.util.List;

public class HelpCommand implements Command {

    public static final String HELP = "B-VSA LS 21/22 Cvičenie 3\n" +
            "Táto aplikácia slúži na demonštráciu práce s SQL databázami pomocou JPA.\n" +
            "Náplňou cvičenia 3 sú jednosmerné asociácie vytvorených entít.\n" +
            "Pre otestovanie aplikácie môžte využiť nasledovné príkazy:\n" +
            "   print <text>    - Výpis poskytnutého textu\n" +
            "   exit, quit, q   - Ukončenie programu\n" +
            "   help, ?         - Výpis tohto textu pomoci\n";

    @Override
    public void execute(String wholeInput, List<String> parameters) {
        System.out.println(HELP);
    }
}
