package ribbon.ex.robot.sensors;

public abstract class Sensor {

    private SensorState state = SensorState.UP;

    public abstract void fixSensor();

    public SensorState getState() {
        return state;
    }

    public void setState(SensorState state) {
        this.state = state;
    }
}
