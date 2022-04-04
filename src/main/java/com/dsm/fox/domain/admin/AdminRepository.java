package com.dsm.fox.domain.admin;

import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    boolean existsByName(String name);
}
