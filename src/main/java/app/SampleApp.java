package app;

import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.LogManager;

public class SampleApp {
    public static void main(String[] args) {
        // Hide info messages about hibernate activities
        // Can also hide warnings by setting level to Level.SEVERE
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = factory.createEntityManager();

        User u = new User("grahamm2", "password1", LocalDate.now());
        System.out.println("User before saving: " + u);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(u);
        transaction.commit();
        System.out.println("User after saving: " + u);
        entityManager.close();
        factory.close();
    }
}
