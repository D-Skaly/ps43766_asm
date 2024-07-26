package skaly.ps43766_asm.utils;

import jakarta.persistence.EntityManager;

public class TransactionUtil {

    /**
     * Performs a database operation within a transaction.
     *
     * @param entityManager the EntityManager
     * @param operation     the operation to perform
     * @return the result of the operation
     */
    public static <T> T performInTransaction(EntityManager entityManager, Operation<T> operation) {
        try {
            EntityManagerUtil.beginTransaction(entityManager);
            T result = operation.execute(entityManager);
            EntityManagerUtil.commitTransaction(entityManager);
            return result;
        } catch (Exception e) {
            EntityManagerUtil.rollbackTransaction(entityManager);
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @FunctionalInterface
    public interface Operation<T> {
        T execute(EntityManager em);
    }
}
