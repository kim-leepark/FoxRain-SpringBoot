package com.dsm.fox.domain.phrase.report;

import com.dsm.fox.domain.phrase.Phrase;
import com.dsm.fox.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity
public class PhraseReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    public User user;
    @ManyToOne(fetch = FetchType.LAZY)
    public Phrase phrase;
}
