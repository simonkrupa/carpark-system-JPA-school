package sk.stuba.fei.uim.vsa.pr1b.ui.command;

import java.util.List;

public interface Command {

    public void execute(String wholeInput, List<String> parameters);

}
