package ua.goit.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppInit  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Init hibernate connector");
        HibernateDatabaseConnector.init();
        System.out.println("Hibernate connector initialisation finished successfully!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Destroy hibernate");
        HibernateDatabaseConnector.destroy();
        System.out.println("Hibernate destroyed");
    }
}
