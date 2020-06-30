package com.home.redditclone.service;

import com.home.redditclone.dto.PostRequest;
import com.home.redditclone.dto.PostResponse;
import com.home.redditclone.exceptions.RedditCloneException;
import com.home.redditclone.mapper.PostMapper;
import com.home.redditclone.model.Post;
import com.home.redditclone.model.SubReddit;
import com.home.redditclone.model.User;
import com.home.redditclone.repository.PostRepository;
import com.home.redditclone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final SubredditRepository subredditRepository;

    public void save(PostRequest postRequest) {
        SubReddit subReddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new RedditCloneException("Could not find subreddit with name: " + postRequest.getSubredditName()));

        User currentUser = authService.getCurrentUser();

        Post post = postMapper.map(postRequest, subReddit, currentUser);
        postRepository.save(post);
    }

    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RedditCloneException("Could not find post by id: " + id));
        return postMapper.map(post);
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::map)
                .collect(Collectors.toList());
    }

    public List<PostResponse> getPostsBySubredditId(Long id) {
        SubReddit subReddit = subredditRepository.findById(id)
                .orElseThrow(() -> new RedditCloneException("Could not find "))
    }

    public List<PostResponse> getPostsByUsername(String username) {
        return null;
    }
}
