package ribbon.ex.robot.sensors;

public class DistanceSensor extends Sensor {

    @Override
    public void fixSensor() {
        String name = "Distance Sensor";
        System.out.println("Fixing : " + name);
        setState(SensorState.UP);
    }
}
