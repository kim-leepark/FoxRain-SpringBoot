package com.dsm.fox.domain.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
@EqualsAndHashCode
public class User {
    @Id
    private Integer id;
    private String email;
    private String name;
    private String password;
}
