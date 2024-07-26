package skaly.ps43766_asm.repository;

import jakarta.persistence.EntityManager;
import skaly.ps43766_asm.entity.Product;
import skaly.ps43766_asm.utils.EntityManagerUtil;

import java.util.List;

public class ProductDao extends BaseDao<Product, Integer> {

    public ProductDao() {
        super(Product.class);
    }

    /**
     * Find products by a specific category.
     * @param categoryId The category ID to filter by.
     * @return A list of products that belong to the given category.
     */
    public List<Product> findByCategory(Integer categoryId) {
        return searchByExactMatch("categoryId", String.valueOf(categoryId));
    }
}
