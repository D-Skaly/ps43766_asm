package skaly.ps43766_asm.repository;

import skaly.ps43766_asm.entity.Category;

public class CategoryDao extends BaseDao<Category, Integer> {

    public CategoryDao() {
        super(Category.class);
    }
}
