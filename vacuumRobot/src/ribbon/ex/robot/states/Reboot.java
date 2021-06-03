package ribbon.ex.robot.states;

import ribbon.ex.robot.IRobot;

public class Reboot implements IRobotState {

    private final IRobot robot;

    Reboot(IRobot robot) {
        this.robot = robot;
    }

    @Override
    public void fix() {
        System.out.println("Robot " + robot.getRobotName() + " is not in need of fixing");
    }

    @Override
    public void clean() {
        System.out.println("Robot " + robot.getRobotName() + " is not active");
    }

    @Override
    public void reboot() {
        System.out.println("Robot " + robot.getRobotName() + " is already rebooting");
    }
}
