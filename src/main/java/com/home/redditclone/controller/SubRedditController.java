package com.home.redditclone.controller;

import com.home.redditclone.dto.SubredditDto;
import com.home.redditclone.service.SubredditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
@Slf4j
public class SubRedditController {

    private final SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubbredit(@RequestBody SubredditDto requestDto) {
        SubredditDto subredditDto = subredditService.save(requestDto);
        return new ResponseEntity<>(subredditDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubReddits() {
        List<SubredditDto> subredditDtoList = subredditService.getAll();
        return new ResponseEntity<>(subredditDtoList, HttpStatus.OK);
    }

}
