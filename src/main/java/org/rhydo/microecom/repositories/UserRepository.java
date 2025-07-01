package org.rhydo.microecom.repositories;

import org.rhydo.microecom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
