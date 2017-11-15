package Task9.tests;

import Task9.app.Application;
import org.junit.*;

public class BaseTest {
    public static ThreadLocal<Application> tApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start(){
        if (tApp.get() !=null){
            app = tApp.get();
            return;
        }


        app = new Application();
        tApp.set(app);
    }

    @After
    public void tearDown() {
        if (tApp.get() != null) {
            app.quit();
            app = null;
        }
    }

}