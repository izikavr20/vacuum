package ribbon.ex.robot.states;

import ribbon.ex.robot.IRobot;

public class Clean implements IRobotState{

    private final IRobot robot;

    Clean(IRobot robot) {
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
        robot.changeState(new Reboot(robot));
        robot.preformRebooting();
        robot.changeState(new Active(robot));
    }
}
