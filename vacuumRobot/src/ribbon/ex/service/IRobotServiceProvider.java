package ribbon.ex.service;

import ribbon.ex.robot.models.IModel;

import java.util.ArrayList;

public interface IRobotServiceProvider {

    void add(String robotName, String wifiName, IModel model);

    void delete(String name);

    void fix(String name);

    void clean(String name);

    void reboot(String name);

    ArrayList<String> getRobotsNames();

    boolean robotNameTaken(String name);

    IModel getModel(String model);

    void terminate();
}
