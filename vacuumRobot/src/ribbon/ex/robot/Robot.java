package ribbon.ex.robot;

import ribbon.ex.robot.models.IModel;
import ribbon.ex.robot.states.IRobotState;
import ribbon.ex.robot.states.StateUtils;

public class Robot implements IRobot{

    private final IModel model;
    private final String name;
    private final String wifiName;

    private volatile IRobotState state;

    public String getWifiName() {
        return wifiName;
    }

    public Robot(IModel model, String name, String wifiName) {
        this.model = model;
        this.name = name;
        this.wifiName = wifiName;
        this.state = StateUtils.getInitialState(this);
    }

    public IModel getModel() {
        return model;
    }

    public String getRobotName() {
        return this.name;
    }

    public void clean() {
        state.clean();
    }

    public void fix() {
        state.fix();
    }

    public void reboot() {
        state.reboot();
    }

    public void changeState(IRobotState newState) { //sync lock or semaphore
        state = newState;
    }

    public void preformCleaning() {
        System.out.println("robot " + name + " is cleaning");
        model.clean();
    }

    public void preformFixing() {
        System.out.println("robot " + name + " is fixing");
        model.fix();
    }

    public void preformRebooting() {
        System.out.println("robot " + name + " is rebooting");
        model.reboot();
    }


}
