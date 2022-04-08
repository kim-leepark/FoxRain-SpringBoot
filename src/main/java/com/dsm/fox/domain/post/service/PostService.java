package com.dsm.fox.domain.post.service;

import com.dsm.fox.domain.post.Post;
import com.dsm.fox.domain.post.PostRepository;
import com.dsm.fox.domain.post.rqrs.PostCreateRq;
import com.dsm.fox.domain.post.rqrs.PostRs;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.BasicException;
import com.dsm.fox.global.exception.exceptions.PostNotFoundException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public PostRs getPost(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        return PostRs.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .userId(post.getUser().getId()).build();
    }

    public void deletePost(int postId) {
        if(!writerCheck(postId)) {
            throw new BasicException("작성자가 아니기때문에 삭제할 수 없습니다", 401);
        }
        postRepository.deleteById(postId);
    }

    private boolean writerCheck(int postId) {
        int id = 2;
        return postRepository.findById(postId).orElseThrow(PostNotFoundException::new)
                .getUser().getId()==id;
    }

    public List<PostRs> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
