package skaly.ps43766_asm.repository;

import java.util.List;
import java.util.Optional;

/**
 * Generic DAO interface for basic CRUD operations.
 * @param <T> The type of the entity
 * @param <K> The type of the entity's identifier
 */
public interface OnlineShopDao<T, K> {

    /**
     * Save the given entity to the database.
     * @param entity The entity to be saved
     * @return The saved entity
     */
    T save(T entity);

    /**
     * Find an entity by its identifier.
     * @param id The identifier of the entity
     * @return An Optional containing the found entity, or empty if not found
     */
    Optional<T> findById(K id);

    /**
     * Update the given entity in the database.
     * @param entity The entity to be updated
     * @return The updated entity
     */
    T update(T entity);

    /**
     * Delete an entity by its identifier.
     * @param id The identifier of the entity to be deleted
     */
    void deleteById(K id);

    /**
     * Find all entities of the type.
     * @return A list of all entities
     */
    List<T> findAll();
}
