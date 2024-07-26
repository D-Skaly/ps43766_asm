package skaly.ps43766_asm.repository;

import skaly.ps43766_asm.entity.Review;

import java.util.List;

public class ReviewDao extends BaseDao<Review, Integer> {

    public ReviewDao() {
        super(Review.class);
    }

    /**
     * Find reviews by product ID.
     * @param productId The product ID to search for.
     * @return A list of reviews for the specified product.
     */
    public List<Review> findByProductId(Integer productId) {
        return searchByExactMatch(String.valueOf(productId), "productId");
    }
}
