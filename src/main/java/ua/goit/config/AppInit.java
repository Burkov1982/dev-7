package ua.goit.config;

import ua.goit.view.ConsoleManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit  implements ServletContextListener {
    private final ConsoleManager manager = new ConsoleManager(System.in, System.out);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        manager.write("Init hibernate connector");
        HibernateDatabaseConnector.init();
        manager.write("Hibernate connector initialisation finished successfully!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        manager.write("Destroy hibernate");
        HibernateDatabaseConnector.destroy();
        manager.write("Hibernate destroyed");
    }
}
