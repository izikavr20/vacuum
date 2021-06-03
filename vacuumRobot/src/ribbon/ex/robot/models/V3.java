package ribbon.ex.robot.models;

import ribbon.ex.robot.sensors.*;

import java.util.ArrayList;
import java.util.List;

public class V3 implements IModel {

    private final List<Sensor> sensorList;


    public V3() {
        sensorList = new ArrayList<>();
        init();
    }

    private void init() {
        sensorList.add(new DistanceSensor());
        sensorList.add(new BrashSensor());
        sensorList.add(new HighSensor());
    }

    @Override
    public void fix() { //v3 exclusive
        for (var sensor : sensorList) {
            if (sensor.getState() == SensorState.DOWN) {
                sensor.fixSensor();
            }
        }
    }

    @Override
    public void clean() { //v3 exclusive
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void reboot() { //v3 exclusive
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
