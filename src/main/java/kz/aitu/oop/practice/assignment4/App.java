package kz.aitu.oop.practice.assignment4;

import kz.aitu.oop.practice.assignment4.models.Employee;

import kz.aitu.oop.practice.assignment4.services.CalculatorProcessor;
import kz.aitu.oop.practice.assignment4.services.ConsoleProcessor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
public class App
{
    public static void main( String[] args ) throws IOException {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session = sessionFactory.openSession();

            ConsoleProcessor cns = new ConsoleProcessor(session);
            cns.start();

            session.getTransaction().commit();
            session.close();
        } catch(Exception exception){
            System.out.println("Problem creating session factory");
            exception.printStackTrace();
        }
    }
}
