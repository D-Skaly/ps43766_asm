package skaly.ps43766_asm.repository;

import skaly.ps43766_asm.entity.OrderItem;

public class OrderItemDao extends BaseDao<OrderItem, Integer> {

    public OrderItemDao() {
        super(OrderItem.class);
    }
}
