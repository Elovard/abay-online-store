package by.tms.abayonlinestore.repository;

import by.tms.abayonlinestore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
