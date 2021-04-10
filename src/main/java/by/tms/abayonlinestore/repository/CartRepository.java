package by.tms.abayonlinestore.repository;

import by.tms.abayonlinestore.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Item, Long> {
}
