package ribbon.ex.robot.sensors;

public class BrashSensor extends Sensor{
    @Override
    public void fixSensor() {
        String name = "Brash Sensor";
        System.out.println("Fixing : " + name);
        setState(SensorState.UP);
    }
}
