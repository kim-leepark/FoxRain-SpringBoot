package com.dsm.fox.domain.post.report;

import com.dsm.fox.domain.post.Post;
import com.dsm.fox.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table
public class PostReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToOne(fetch = FetchType.LAZY)
    private Post post;
}
