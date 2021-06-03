package ribbon.ex.robot;

import ribbon.ex.robot.states.IRobotState;

public interface IRobot {

    void preformRebooting();

    void preformFixing();

    void preformCleaning();

    void changeState(IRobotState newState);

    void reboot();

    void fix();

    void clean();

    String getRobotName();
}

