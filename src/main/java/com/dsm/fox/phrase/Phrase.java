package com.dsm.fox.phrase;

import com.dsm.fox.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Builder
@Getter
@Entity
@Table
public class Phrase {
    @Id
    private Integer id;
    private String content;
    private String man;
    @ManyToOne
    private User user;
}
