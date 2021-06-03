package ribbon.ex.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ribbon.ex.service.VacuumServiceProvider;
import ribbon.ex.robot.models.V1;

import static org.junit.jupiter.api.Assertions.*;

class VacuumServiceProviderTest {

    private VacuumServiceProvider provider;

    @BeforeEach
    void setUp() {
        provider = new VacuumServiceProvider(5);
    }

    @Test
    void add() {
        String[] names = {"a", "b", "c", "d", "e", "f", "j"};
        addRobots(names);
        assertEquals(names.length,provider.getRobotsNames().size());
    }

    private void addRobots(String[] names) {
        int i = 0;
        for (var name : names) {
            provider.add(name, name, new V1());
            assertEquals(name, provider.getRobotsNames().get(i));
            i++;
        }
    }

    @Test
    void delete() {
        String[] names = {"a", "b", "c", "d", "e", "f", "j"};
        addRobots(names);
        removeRobots(names);
        assertEquals(0,provider.getRobotsNames().size());
    }

    private void removeRobots(String[] names) {
        for (var name : names) {
           provider.delete(name);
        }
    }
}