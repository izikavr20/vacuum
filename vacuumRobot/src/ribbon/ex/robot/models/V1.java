package ribbon.ex.robot.models;

import ribbon.ex.robot.sensors.DistanceSensor;
import ribbon.ex.robot.sensors.Sensor;
import ribbon.ex.robot.sensors.SensorState;

import java.util.ArrayList;
import java.util.List;

public class V1 implements IModel {

    private final List<Sensor> sensorList;

    public V1() {
        sensorList = new ArrayList<>();
        init();
    }

    private void init() {
        sensorList.add(new DistanceSensor());
    }

    @Override
    public void fix() { //v1 exclusive
        for (var sensor : sensorList) {
            if (sensor.getState() == SensorState.DOWN) {
                sensor.fixSensor();
            }
        }
    }

    @Override
    public void clean() { //v1 exclusive
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) { //cheating
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void reboot() { //v1 exclusive
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
