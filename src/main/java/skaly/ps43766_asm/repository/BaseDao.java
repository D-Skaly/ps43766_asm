package skaly.ps43766_asm.repository;

import skaly.ps43766_asm.utils.EntityManagerUtil;
import skaly.ps43766_asm.utils.TransactionUtil;
import skaly.ps43766_asm.utils.FilterUtil;
import skaly.ps43766_asm.utils.PaginationUtil;
import skaly.ps43766_asm.utils.SearchUtil;

import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Base DAO class providing basic CRUD operations and additional methods for pagination, searching, and filtering.
 * @param <T> The type of the entity
 * @param <K> The type of the entity's identifier
 */
public abstract class BaseDao<T, K> implements PagedSearchableDao<T, K> {

    protected final Class<T> entityClass;

    public BaseDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        return TransactionUtil.performInTransaction(em, entityManager -> {
            entityManager.persist(entity);
            return entity;
        });
    }

    @Override
    public Optional<T> findById(K id) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return Optional.ofNullable(em.find(entityClass, id));
        }
    }

    @Override
    public T update(T entity) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        return TransactionUtil.performInTransaction(em, entityManager -> entityManager.merge(entity));
    }

    @Override
    public void deleteById(K id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        TransactionUtil.performInTransaction(em, entityManager -> {
            T entity = entityManager.find(entityClass, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
            return null;
        });
    }

    @Override
    public List<T> findAll() {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
        }
    }

    @Override
    public List<T> getPaginatedList(int pageNumber, int pageSize) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return PaginationUtil.getPaginatedList(em, pageNumber, pageSize, entityClass);
        }
    }

    @Override
    public List<T> filterByFields(Map<String, Object> filters) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return FilterUtil.filter(em, filters, entityClass);
        }
    }

    @Override
    public List<T> searchByKeyword(String keyword, String field) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return SearchUtil.searchByKeyword(em, keyword, field, entityClass);
        }
    }

    @Override
    public List<T> searchByExactMatch(String keyword, String field) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return SearchUtil.searchByExactMatch(em, keyword, field, entityClass);
        }
    }

    @Override
    public List<T> searchByMultipleFields(String keyword, List<String> fields) {
        try (EntityManager em = EntityManagerUtil.getEntityManager()) {
            return SearchUtil.searchByMultipleFields(em, keyword, fields, entityClass);
        }
    }
}
