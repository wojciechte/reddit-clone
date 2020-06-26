package com.home.redditclone.mapper;

import com.home.redditclone.dto.SubredditDto;
import com.home.redditclone.model.Post;
import com.home.redditclone.model.SubReddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subReddit.getPosts()))")
    SubredditDto mapSubredditToDto(SubReddit subReddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    SubReddit mapDtoToSubreddit(SubredditDto subredditDto);
}
