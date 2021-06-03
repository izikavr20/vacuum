package ribbon.ex.robot.states;

import ribbon.ex.robot.IRobot;

public class StateUtils {

    static public IRobotState getInitialState(IRobot robot) {

        if (getState()) {
            return new Failure(robot);
        }
        return new Active(robot);
    }

    static private boolean getState() {
        double randomNumber = Math.random();//good enough random
        return randomNumber >= 0.79;
    }
}
