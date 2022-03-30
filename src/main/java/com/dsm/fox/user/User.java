package com.dsm.fox.user;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Entity
@Table
public class User {
    @Id
    private Integer id;
    private String email;
    private String name;
}
