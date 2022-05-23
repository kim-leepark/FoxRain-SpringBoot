package com.dsm.fox.domain.post.service;

import com.dsm.fox.domain.post.Post;
import com.dsm.fox.domain.post.PostRepository;
import com.dsm.fox.domain.post.report.PostReportRepository;
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
    private final PostReportRepository reportRepository;

    public void postCreate(PostCreateRq rq, User user) {
        postRepository.save(
                Post.builder()
                .title(rq.getTitle())
                .content(rq.getContent())
                .createdAt(LocalDate.now())
                .user(user).build()
        );
    }

    public PostRs getPost(int id, User user) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return PostRs.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .isReport(userReportCheck(user, id))
                .userId(post.getUser().getId()).build();
    }

    public boolean userReportCheck(User user, int postId) {
        if(user==null) {
            return false;
        }
        return reportRepository.existsByUserIdAndPostId(user.getId(), postId);
    }

    public void deletePost(int id, User user) {
        if(!writerCheck(id, user)) {
            throw new BasicException("작성자가 아니기때문에 삭제할 수 없습니다", 401);
        }
        postRepository.deleteById(id);
    }

    private boolean writerCheck(int id, User user) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new)
                .getUser().equals(user);
    }

    public List<PostRs> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
