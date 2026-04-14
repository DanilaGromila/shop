package org.gromila.shopapp.repository;

import org.gromila.shopapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}