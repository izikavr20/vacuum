package ribbon.ex.robot.models;

import ribbon.ex.robot.sensors.BrashSensor;
import ribbon.ex.robot.sensors.DistanceSensor;
import ribbon.ex.robot.sensors.Sensor;
import ribbon.ex.robot.sensors.SensorState;

import java.util.ArrayList;
import java.util.List;

public class V2 implements IModel {

    private final List<Sensor> sensorList;


    public V2() {
        sensorList = new ArrayList<>();
        init();
    }

    private void init() {
        sensorList.add(new DistanceSensor());
        sensorList.add(new BrashSensor());
    }

    @Override
    public void fix() { //v2 exclusive
        for (var sensor : sensorList) {
            if (sensor.getState() == SensorState.DOWN) {
                sensor.fixSensor();
            }
        }
    }

    @Override
    public void clean() { //v2 exclusive
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void reboot() { //v2 exclusive
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
