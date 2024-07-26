package skaly.ps43766_asm.repository;

import java.util.List;
import java.util.Map;

/**
 * Interface providing additional functionalities for pagination, searching, and filtering.
 * @param <T> The type of the entity
 * @param <K> The type of the entity's identifier
 */
public interface PagedSearchableDao<T, K> extends OnlineShopDao<T, K> {

    /**
     * Retrieve a paginated list of entities.
     * @param pageNumber The page number (starting from 1).
     * @param pageSize The number of items per page.
     * @return A list of entities for the specified page.
     */
    List<T> getPaginatedList(int pageNumber, int pageSize);

    /**
     * Filter entities based on given criteria.
     * @param filters A map of field names and their values to filter.
     * @return A list of entities that match the filter criteria.
     */
    List<T> filterByFields(Map<String, Object> filters);

    /**
     * Search entities by a keyword in a specific field.
     * @param keyword The keyword to search for.
     * @param field The field to search in.
     * @return A list of entities that match the search criteria.
     */
    List<T> searchByKeyword(String keyword, String field);

    /**
     * Search entities by exact match in a specific field.
     * @param keyword The keyword to search for.
     * @param field The field to search in.
     * @return A list of entities that match the exact search criteria.
     */
    List<T> searchByExactMatch(String keyword, String field);

    /**
     * Search entities by keyword across multiple fields.
     * @param keyword The keyword to search for.
     * @param fields The list of fields to search within.
     * @return A list of entities that match the search criteria in any of the specified fields.
     */
    List<T> searchByMultipleFields(String keyword, List<String> fields);
}
