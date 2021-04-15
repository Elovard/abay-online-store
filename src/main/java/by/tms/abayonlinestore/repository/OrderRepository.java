package by.tms.abayonlinestore.repository;

import by.tms.abayonlinestore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
