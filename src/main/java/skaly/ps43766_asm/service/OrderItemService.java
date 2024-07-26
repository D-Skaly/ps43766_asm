package skaly.ps43766_asm.service;

import skaly.ps43766_asm.entity.OrderItem;
import skaly.ps43766_asm.repository.OrderItemDao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderItemService extends BaseService<OrderItem, Integer> {

    public OrderItemService(OrderItemDao dao) {
        super(dao);
    }

    // Các phương thức CRUD kế thừa từ BaseService

    // Các phương thức phân trang, tìm kiếm, và lọc kế thừa từ BaseService
}
