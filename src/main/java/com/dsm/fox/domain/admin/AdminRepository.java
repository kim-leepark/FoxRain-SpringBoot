package com.dsm.fox.domain.admin;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    boolean existsByName(String name);
    Optional<Admin> findByName(String name);
}
