package com.fakeSocialMedia.fakeSocialMedia.controller;

import com.fakeSocialMedia.fakeSocialMedia.dto.PostDTO;
import com.fakeSocialMedia.fakeSocialMedia.service.PostService;
import com.fakeSocialMedia.fakeSocialMedia.service.serviceImp.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts/{accountId}")
    public ResponseEntity<List<PostDTO>> getAllPosts(@PathVariable String accountId){
        List<PostDTO> postDTOList = postService.getAllPostByAccountId(accountId);
        return ResponseEntity.ok(postDTOList);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createNewPost(@RequestBody PostDTO postDTO){
        PostDTO processedPostDTO = postService.createNewPost(postDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(processedPostDTO);
    }

    @PostMapping("/dumpLoadOfPosts")
    public ResponseEntity<List<PostDTO>> dumpLoadOfPosts(@RequestBody List<PostDTO> postDTOList){
        List<PostDTO> postDTOS = postService.dumpLoadOfPosts(postDTOList);
        return ResponseEntity.status(HttpStatus.CREATED).body(postDTOS);
    }

}
