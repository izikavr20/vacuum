package ribbon.ex;

import ribbon.ex.service.VacuumServiceProvider;
import ribbon.ex.ui.CommandLineUI;

public class Main {

    public static void main(String[] args) {

        CommandLineUI ui = new CommandLineUI(new VacuumServiceProvider(5));
        ui.startUI();
    }
}
