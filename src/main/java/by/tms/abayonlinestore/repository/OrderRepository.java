package by.tms.abayonlinestore.repository;

import by.tms.abayonlinestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    public Order findOrderByOrderId(Long orderId);
}
