package com.home.redditclone.service;

import com.home.redditclone.dto.SubredditDto;
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

    final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        SubReddit subReddit = subredditRepository.save(mapSubredditDto(subredditDto));
        subredditDto.setId(subReddit.getId());
        return subredditDto;
    }

    @Transactional
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SubredditDto mapToDto(SubReddit subReddit) {
        SubredditDto dto = new SubredditDto();
        dto.setId(subReddit.getId());
        dto.setName(subReddit.getName());
        dto.setDescription(subReddit.getDescription());
        dto.setNumberOfPosts(subReddit.getPosts().size());
        return dto;
    }

    private SubReddit mapSubredditDto(SubredditDto subredditDto) {
        return SubReddit.builder().name(subredditDto.getName())
                .description(subredditDto.getDescription())
                .build();
    }
}
