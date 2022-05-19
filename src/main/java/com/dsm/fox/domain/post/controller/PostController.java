package com.dsm.fox.domain.post.controller;

import com.dsm.fox.domain.post.rqrs.PostCreateRq;
import com.dsm.fox.domain.post.rqrs.PostRs;
import com.dsm.fox.domain.post.service.PostService;
import com.dsm.fox.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public void postCreate(@AuthenticationPrincipal final User user, @RequestBody PostCreateRq rq) {
        postService.postCreate(rq, user);
    }

    @GetMapping("/post/{postId}")
    public PostRs getPost(@PathVariable int postId){
        return postService.getPost(postId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/post/{postId}")
    public void deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
    }

    @GetMapping("/posts")
    public List<PostRs> getPostList(final Pageable pageable) {
        return postService.getPostList(pageable);
    }
}
