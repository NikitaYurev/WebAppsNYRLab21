package csit.semit.nyr.webappsnyrlab21.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (HibernateException ex) {
            // Log the exception if configuration fails
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Method to get the SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Method to get a new Session instance
    public static Session getSession() {
        return getSessionFactory().openSession();
    }

    // Shutdown method to close caches and connection pools
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

