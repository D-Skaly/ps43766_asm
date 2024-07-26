package skaly.ps43766_asm.repository;

import skaly.ps43766_asm.entity.Order;

import java.util.List;

public class OrderDao extends BaseDao<Order, Integer> {

    public OrderDao() {
        super(Order.class);
    }


    /**
     * Find carts by a specific user ID.
     * @param customerId The customer ID to filter by.
     * @return A list of carts that belong to the given user.
     */
    public List<Order> findByCustomerId(Integer customerId) {
       return searchByKeyword("customerID", String.valueOf(customerId));
    }
}
