package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.Review;
import skaly.ps43766_asm.repository.ReviewDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ReviewService extends BaseService<Review, Integer> {

    public ReviewService() {
        super(new ReviewDao());
    }

    /**
     * Find reviews by product ID.
     * @param productId The product ID to search for.
     * @return A list of reviews for the specified product.
     */
    public List<Review> findByProductId(Integer productId) {
        return ((ReviewDao) dao).findByProductId(productId);
    }

}
