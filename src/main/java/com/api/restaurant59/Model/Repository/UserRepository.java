package com.api.restaurant59.Model.Repository;

import com.api.restaurant59.Model.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
