package ru.strict.ioc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.strict.ioc.components.Controller1;

@RunWith(JUnit4.class)
public class IoCTest {

    private static final IoC IOC = new IoCImpl();

    @Test
    public void testGetComponent_list() {
        Controller1 controller1 = IOC.getComponent(Controller1.class);
    }
}
