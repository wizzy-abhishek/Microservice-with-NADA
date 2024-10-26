package com.fakeSocialMedia.fakeSocialMedia.controller;

import com.fakeSocialMedia.fakeSocialMedia.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post/")
public class PostController {

    private final PostService postService ;

    public PostController(PostService postService) {
        this.postService = postService;
    }



}
