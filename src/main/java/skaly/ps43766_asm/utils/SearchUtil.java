package skaly.ps43766_asm.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

/**
 * Utility class for search operations using Criteria API.
 */
public class SearchUtil {

    /**
     * Performs a search by keyword in a specific field of an entity using Criteria API.
     *
     * @param em      The EntityManager instance
     * @param keyword The keyword to search for
     * @param field   The field to be searched
     * @param clazz   The class of the entity
     * @param <T>     The type of the entity
     * @return A list of entities that match the search criteria
     */
    public static <T> List<T> searchByKeyword(EntityManager em, String keyword, String field, Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        // Create the search predicate
        Predicate searchPredicate = cb.like(cb.lower(root.get(field)), "%" + keyword.toLowerCase() + "%");

        // Apply the predicate to the query
        cq.where(searchPredicate);

        // Create and execute the query
        return em.createQuery(cq).getResultList();
    }

    /**
     * Search entities by exact match in a specific field.
     *
     * @param em      The EntityManager.
     * @param keyword The keyword to search for.
     * @param field   The field to search within.
     * @param clazz   The class type of the entity.
     * @param <T>     The type of the entity.
     * @return A list of entities that match the exact search criteria.
     */
    public static <T> List<T> searchByExactMatch(EntityManager em, String keyword, String field, Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        // Create the exact match predicate
        Predicate exactMatchPredicate = cb.equal(cb.lower(root.get(field)), keyword.toLowerCase());

        // Apply the predicate to the query
        cq.where(exactMatchPredicate);

        // Create and execute the query
        return em.createQuery(cq).getResultList();
    }

    /**
     * Search entities by keyword across multiple fields.
     *
     * @param em      The EntityManager.
     * @param keyword The keyword to search for.
     * @param fields  The list of fields to search within.
     * @param clazz   The class type of the entity.
     * @param <T>     The type of the entity.
     * @return A list of entities that match the search criteria in any of the specified fields.
     */
    public static <T> List<T> searchByMultipleFields(EntityManager em, String keyword, List<String> fields, Class<T> clazz) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        Root<T> root = cq.from(clazz);

        // Create predicates for each field
        Predicate[] predicates = fields.stream()
                .map(field -> cb.like(cb.lower(root.get(field)), "%" + keyword.toLowerCase() + "%"))
                .toArray(Predicate[]::new);

        // Combine all predicates with OR
        Predicate combinedPredicate = cb.or(predicates);

        // Apply the predicate to the query
        cq.where(combinedPredicate);

        // Create and execute the query
        return em.createQuery(cq).getResultList();
    }
}
