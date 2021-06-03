package ribbon.ex.robot.sensors;

public class HighSensor extends Sensor{
    @Override
    public void fixSensor() {
        String name = "High Sensor";
        System.out.println("Fixing : " + name);
        setState(SensorState.UP);
    }
}
