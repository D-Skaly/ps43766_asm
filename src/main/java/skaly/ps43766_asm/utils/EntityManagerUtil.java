package skaly.ps43766_asm.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * Utility class for managing EntityManager and transactions.
 */
public class EntityManagerUtil {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");

    private EntityManagerUtil() {
        // Private constructor to prevent instantiation
    }

    /**
     * Creates and returns a new EntityManager instance.
     * @return A new EntityManager instance
     */
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Begins a transaction on the given EntityManager.
     * @param em The EntityManager instance
     */
    public static void beginTransaction(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        if (!transaction.isActive()) {
            transaction.begin();
        }
    }

    /**
     * Commits the current transaction on the given EntityManager.
     * @param em The EntityManager instance
     */
    public static void commitTransaction(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        if (transaction.isActive()) {
            transaction.commit();
        }
    }

    /**
     * Rolls back the current transaction on the given EntityManager.
     * @param em The EntityManager instance
     */
    public static void rollbackTransaction(EntityManager em) {
        EntityTransaction transaction = em.getTransaction();
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }

    /**
     * Closes the EntityManagerFactory if it is open.
     */
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}
