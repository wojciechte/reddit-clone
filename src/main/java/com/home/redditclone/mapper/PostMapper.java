package com.home.redditclone.mapper;

import com.home.redditclone.dto.PostRequest;
import com.home.redditclone.dto.PostResponse;
import com.home.redditclone.model.Post;
import com.home.redditclone.model.SubReddit;
import com.home.redditclone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "request.description")
    Post map(PostRequest request, SubReddit subReddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "username", source = "user.username")
    PostResponse map(Post post);

}
