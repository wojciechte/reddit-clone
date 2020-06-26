package com.home.redditclone.service;

import com.home.redditclone.dto.SubredditDto;
import com.home.redditclone.exceptions.RedditCloneMailException;
import com.home.redditclone.mapper.SubredditMapper;
import com.home.redditclone.model.SubReddit;
import com.home.redditclone.repository.SubredditRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        SubReddit subReddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subReddit.getId());
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll().stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }

    public SubredditDto getSubreddit(Long id) {
        SubReddit subReddit = subredditRepository.findById(id)
                .orElseThrow(() -> new RedditCloneMailException("No subreddit found with id: " + id));
        return subredditMapper.mapSubredditToDto(subReddit);
    }
}
