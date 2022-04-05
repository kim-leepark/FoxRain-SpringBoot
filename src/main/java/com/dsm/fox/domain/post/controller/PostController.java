package com.dsm.fox.domain.post.controller;

import com.dsm.fox.domain.post.rqrs.PostCreateRq;
import com.dsm.fox.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public void postCreate(@RequestBody PostCreateRq rq) {
    }
}
