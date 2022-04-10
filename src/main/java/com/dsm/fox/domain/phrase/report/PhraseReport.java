package com.dsm.fox.domain.phrase.report;

import com.dsm.fox.domain.phrase.Phrase;
import com.dsm.fox.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class PhraseReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String content;
    @ManyToOne
    public User user;
    @ManyToOne
    public Phrase phrase;
}
