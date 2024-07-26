package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.Category;
import skaly.ps43766_asm.repository.CategoryDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CategoryService extends BaseService<Category, Integer> {

    public CategoryService(CategoryDao dao) {
        super(dao);
    }

    // Các phương thức CRUD kế thừa từ BaseService

    // Các phương thức phân trang, tìm kiếm, và lọc kế thừa từ BaseService
}
