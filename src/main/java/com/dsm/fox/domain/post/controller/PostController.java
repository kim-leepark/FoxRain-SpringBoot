package com.dsm.fox.domain.post.controller;

import com.dsm.fox.domain.post.rqrs.PostCreateRq;
import com.dsm.fox.domain.post.rqrs.PostRs;
import com.dsm.fox.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public void postCreate(@RequestBody PostCreateRq rq) {
        postService.postCreate(rq);
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
    public void getPostList() {

    }
}
