package skaly.ps43766_asm.repository;

import jakarta.persistence.EntityManager;
import skaly.ps43766_asm.entity.Cart;
import skaly.ps43766_asm.utils.EntityManagerUtil;

import java.util.List;

public class CartDao extends BaseDao<Cart, Integer> {

    public CartDao() {
        super(Cart.class);
    }

    /**
     * Find carts by a specific user ID.
     * @param userId The user ID to filter by.
     * @return A list of carts that belong to the given user.
     */
    public List<Cart> findByUserId(Integer userId) {
        return searchByKeyword(String.valueOf(userId), "userId");
    }
}
