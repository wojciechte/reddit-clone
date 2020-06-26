package com.home.redditclone.service;

import com.home.redditclone.dto.PostRequest;
import com.home.redditclone.dto.PostResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
    
    public void save(PostRequest postRequest) {

    }

    public PostResponse getPost(Long id) {
        return null;
    }

    public List<PostResponse> getAllPosts() {
        return null;
    }

    public List<PostResponse> getPostsBySubredditId(Long id) {
        return null;
    }

    public List<PostResponse> getPostsByUsername(String username) {
        return null;
    }
}
