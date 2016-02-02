package net.zencal.snuffles.data_access;

import net.zencal.snuffles.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    User findByDubUserId(String dubUserId);
}
