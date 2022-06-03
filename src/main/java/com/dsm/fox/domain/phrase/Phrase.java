package com.dsm.fox.domain.phrase;

import com.dsm.fox.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table
public class Phrase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private String man;
    @ColumnDefault("0")
    private int reportNum;
    @ManyToOne
    private User user;

    public void countReport() {
        this.reportNum = reportNum+1;
    }
}
