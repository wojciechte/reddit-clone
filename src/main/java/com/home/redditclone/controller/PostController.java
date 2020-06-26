package com.home.redditclone.controller;

import com.home.redditclone.dto.PostRequest;
import com.home.redditclone.dto.PostResponse;
import com.home.redditclone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostRequest postRequest) {
        postService.save(postRequest);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        PostResponse response = postService.getPost(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> responseList = postService.getAllPosts();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/subreddit/{id}")
    public ResponseEntity<List<PostResponse>> getPostsBySubreddit(@PathVariable Long id) {
        List<PostResponse> responseList = postService.getPostsBySubredditId(id);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<PostResponse>> getPostsByUsername(@PathVariable String username) {
        List<PostResponse> responseList = postService.getPostsByUsername(username);
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }
}
