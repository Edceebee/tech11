package com.Administrator.tech11.repositories;

import com.Administrator.tech11.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Page<Users> findAll(Pageable pageable);

    Optional<Users> findByEmail(String email);

}
