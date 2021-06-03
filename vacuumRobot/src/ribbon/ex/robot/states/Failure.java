package ribbon.ex.robot.states;

import ribbon.ex.robot.IRobot;

public class Failure implements IRobotState {

    private final IRobot robot;

    Failure(IRobot robot) {
        this.robot = robot;
    }

    @Override
    public void fix() {

        robot.changeState(new Reboot(robot));
        robot.preformFixing();
        robot.changeState(new Active(robot));
    }

    @Override
    public void clean() {
        System.out.println("Robot " + robot.getRobotName() + " is not active");
    }

    @Override
    public void reboot() {

        robot.changeState(new Reboot(robot));
        robot.preformRebooting();
        robot.changeState(this); //should come back to failure if he entered reboot from there
    }
}
