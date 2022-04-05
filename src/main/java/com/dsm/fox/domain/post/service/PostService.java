package com.dsm.fox.domain.post.service;

import com.dsm.fox.domain.post.Post;
import com.dsm.fox.domain.post.PostRepository;
import com.dsm.fox.domain.post.rqrs.PostCreateRq;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void postCreate(PostCreateRq rq) {
        int id = 1; // token에서 빼내온 user id
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        postRepository.save(
                Post.builder()
                .title(rq.getTitle())
                .content(rq.getContent())
                .createdAt(LocalDate.now())
                .user(user).build()
        );
    }
}
