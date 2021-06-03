package ribbon.ex.robot.states;

import ribbon.ex.robot.IRobot;

public class Active implements IRobotState {

    private final IRobot robot;

    public Active(IRobot robot) {
        this.robot = robot;
    }

    @Override
    public void fix() {
        System.out.println("Robot " + robot.getRobotName() + " is not in need of fixing");
    }

    @Override
    public void clean() {
        robot.changeState(new Clean(robot));
        robot.preformCleaning();
        robot.changeState(this);
    }

    @Override
    public void reboot() {
        robot.changeState(new Reboot(robot));
        robot.preformRebooting();
        robot.changeState(this);
    }
}
