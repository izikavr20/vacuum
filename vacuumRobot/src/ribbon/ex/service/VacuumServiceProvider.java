package ribbon.ex.service;

import ribbon.ex.robot.IRobot;
import ribbon.ex.robot.Robot;
import ribbon.ex.robot.models.IModel;
import ribbon.ex.robot.models.V1;
import ribbon.ex.robot.models.V2;
import ribbon.ex.robot.models.V3;

import java.util.ArrayList;
import java.util.concurrent.*;

public class VacuumServiceProvider implements IRobotServiceProvider {

    private final ConcurrentHashMap<String, IRobot> robots = new ConcurrentHashMap<>();
    private final ScheduledThreadPoolExecutor threadPoolExecutor;
    private final ConcurrentHashMap<String, FutureTask<?>> tasksToCancel = new ConcurrentHashMap<>();

    public VacuumServiceProvider(int threadCapacity) {

        threadPoolExecutor = new ScheduledThreadPoolExecutor(threadCapacity);
        threadPoolExecutor.setRemoveOnCancelPolicy(true);
    }

    @Override
    public void add(String robotName, String wifiName, IModel model) {
        if (!robots.containsKey(robotName)) {
            robots.put(robotName, new Robot(model, robotName, wifiName));
        } else {
            System.out.println("robot name is already in use, returning to menu");
        }
    }

    /**
     * doesn't stop current robot action if he is deleted
     */
    @Override
    public void delete(String name) {

        if (robots.remove(name) == null) {
            System.out.println("robot " + name + " do not exist");
        }
        System.out.println("robot " + name + " have been removed");
    }

    @Override
    public void clean(String name) {

        Future<?> future = threadPoolExecutor.submit(() -> robots.get(name).clean());
        tasksToCancel.put(name, (FutureTask<?>) future);
    }

    @Override
    public void reboot(String name) {

        var future = tasksToCancel.remove(name);
        if(!future.isDone()) {
            future.cancel(true);
            System.out.println("canceling");
        }
        threadPoolExecutor.execute(() -> robots.get(name).reboot());
    }

    @Override
    public ArrayList<String> getRobotsNames() {

        ArrayList<String> list = new ArrayList<>();
        robots.forEach((k, v) -> list.add(k));
        return list;
    }

    @Override
    public boolean robotNameTaken(String name) {
        return robots.containsKey(name);
    }

    @Override
    public IModel getModel(String model) {

        return switch (model) {
            case "2" -> new V2();
            case "3" -> new V3();
            default -> new V1();
        };
    }

    @Override
    public void terminate() {
        threadPoolExecutor.shutdown();
    }

    @Override
    public void fix(String name) {

        Future<?> future = threadPoolExecutor.submit(() -> robots.get(name).fix());
        tasksToCancel.put(name, (FutureTask<?>) future);
    }
}
