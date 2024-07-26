package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.Order;
import skaly.ps43766_asm.repository.OrderDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderService extends BaseService<Order, Integer> {

    public OrderService(OrderDao dao) {
        super(dao);
    }


    /**
     * Find orders by customer ID.
     * @param customerId The customer ID to search for.
     * @return A list of orders made by the specified customer.
     */
    public List<Order> findByCustomerId(Integer customerId) {
        return ((OrderDao) dao).findByCustomerId(customerId);
    }

}
