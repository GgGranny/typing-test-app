package com.typing_test_app.typing_app.repository;

import com.typing_test_app.typing_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    public User findByUsername(String username);

    boolean existsByUsername(String username);
}
