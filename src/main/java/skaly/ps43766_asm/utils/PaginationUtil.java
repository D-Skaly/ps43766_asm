package skaly.ps43766_asm.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

/**
 * Utility class for pagination operations using Criteria API.
 */
public class PaginationUtil {

    /**
     * Retrieves a paginated list of entities using Criteria API.
     * @param em The EntityManager instance
     * @param pageNumber The page number (starting from 1)
     * @param pageSize The number of items per page
     * @param clazz The class of the entity
     * @param <T> The type of the entity
     * @return A list of entities for the specified page
     */
    public static <T> List<T> getPaginatedList(EntityManager em, int pageNumber, int pageSize, Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        // Build the query
        CriteriaQuery<T> query = cq.select(root);

        // Create and execute the query
        return em.createQuery(query)
                .setFirstResult((pageNumber - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }
}
