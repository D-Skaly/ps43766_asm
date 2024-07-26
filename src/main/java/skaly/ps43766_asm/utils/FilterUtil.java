package skaly.ps43766_asm.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Map;

/**
 * Utility class for filtering operations using Criteria API.
 */
public class FilterUtil {

    /**
     * Performs filtering based on given criteria using Criteria API.
     * @param em The EntityManager instance
     * @param filters A map of field names and their values to filter
     * @param clazz The class of the entity
     * @param <T> The type of the entity
     * @return A list of entities that match the filter criteria
     */
    public static <T> List<T> filter(EntityManager em, Map<String, Object> filters, Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        // Create predicates for each filter
        Predicate[] predicates = filters.entrySet().stream()
                .map(entry -> cb.equal(root.get(entry.getKey()), entry.getValue()))
                .toArray(Predicate[]::new);

        // Apply predicates to the query
        cq.where(predicates);

        // Create and execute the query
        return em.createQuery(cq).getResultList();
    }
}
