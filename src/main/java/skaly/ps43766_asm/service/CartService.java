package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.Cart;
import skaly.ps43766_asm.repository.CartDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CartService extends BaseService<Cart, Integer> {

    public CartService(CartDao dao) {
        super(dao);
    }

    /**
     * Find carts by user ID.
     * @param userId The user ID to search for.
     * @return A list of carts belonging to the specified user.
     */
    public List<Cart> findByUserId(Integer userId) {
        return ((CartDao) dao).findByUserId(userId);
    }
}
