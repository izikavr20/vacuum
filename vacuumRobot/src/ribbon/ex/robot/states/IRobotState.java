package ribbon.ex.robot.states;

/**
this interface could have had default methods and save a lot of code reuse
but it would have made the state classes harder to read
 */
public interface IRobotState {

    void fix();

    void clean();

    void reboot();
}
