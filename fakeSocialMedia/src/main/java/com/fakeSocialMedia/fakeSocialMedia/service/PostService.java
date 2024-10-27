package com.fakeSocialMedia.fakeSocialMedia.service;

import com.fakeSocialMedia.fakeSocialMedia.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPostByAccountId(String accountId);

    PostDTO createNewPost(PostDTO postDTO);
}
