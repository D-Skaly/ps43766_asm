package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.Product;
import skaly.ps43766_asm.repository.BaseDao;
import skaly.ps43766_asm.repository.PagedSearchableDao;
import skaly.ps43766_asm.repository.ProductDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductService extends BaseService<Product, Integer> {


    public ProductService(){
        super(new ProductDao());
    }

    // Các phương thức CRUD kế thừa từ BaseService

    /**
     * Find products by category.
     * @param categoryId The category ID to search for.
     * @return A list of products in the specified category.
     */
    public List<Product> findByCategory(Integer categoryId) {
        return ((ProductDao) dao).findByCategory(categoryId);
    }

    // Các phương thức phân trang, tìm kiếm, và lọc kế thừa từ BaseService
}
