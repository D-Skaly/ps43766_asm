package skaly.ps43766_asm.service;

import skaly.ps43766_asm.repository.BaseDao;
import skaly.ps43766_asm.repository.PagedSearchableDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Base service class providing basic CRUD operations as well as pagination, search, and filtering.
 * @param <T> The type of the entity
 * @param <K> The type of the entity's identifier
 */
public abstract class BaseService<T, K> {

    protected final BaseDao<T, K> dao;

    public BaseService(BaseDao<T, K> dao) {
        this.dao = dao;
    }

    // CRUD operations
    public T save(T entity) {
        return dao.save(entity);
    }

    public Optional<T> findById(K id) {
        return dao.findById(id);
    }

    public T update(T entity) {
        return dao.update(entity);
    }

    public void deleteById(K id) {
        dao.deleteById(id);
    }

    public List<T> findAll() {
        return dao.findAll();
    }

    // Pagination
    public List<T> getPaginatedList(int pageNumber, int pageSize) {
        return dao.getPaginatedList(pageNumber, pageSize);
    }

    // Filtering
    public List<T> filterByFields(Map<String, Object> filters) {
        return dao.filterByFields(filters);
    }

    // Search
    public List<T> searchByKeyword(String keyword, String field) {
        return dao.searchByKeyword(keyword, field);
    }

    public List<T> searchByExactMatch(String keyword, String field) {
        return dao.searchByExactMatch(keyword, field);
    }

    public List<T> searchByMultipleFields(String keyword, List<String> fields) {
        return dao.searchByMultipleFields(keyword, fields);
    }
}
