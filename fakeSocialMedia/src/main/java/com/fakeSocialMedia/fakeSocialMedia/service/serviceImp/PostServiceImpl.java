package com.fakeSocialMedia.fakeSocialMedia.service.serviceImp;

import com.fakeSocialMedia.fakeSocialMedia.dto.PostDTO;
import com.fakeSocialMedia.fakeSocialMedia.entity.Account;
import com.fakeSocialMedia.fakeSocialMedia.entity.Post;
import com.fakeSocialMedia.fakeSocialMedia.exceptions.ResourceNotFoundException;
import com.fakeSocialMedia.fakeSocialMedia.repository.AccountRepo;
import com.fakeSocialMedia.fakeSocialMedia.repository.PostRepo;
import com.fakeSocialMedia.fakeSocialMedia.service.AccountService;
import com.fakeSocialMedia.fakeSocialMedia.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo ;
    private final ModelMapper modelMapper ;
    private final AccountRepo accountRepo;
    private final AccountServiceImplement accountServiceImplement ;

    public PostServiceImpl(PostRepo postRepo, ModelMapper modelMapper, AccountRepo accountRepo, AccountServiceImplement accountServiceImplement) {
        this.postRepo = postRepo;
        this.modelMapper = modelMapper;
        this.accountRepo = accountRepo;
        this.accountServiceImplement = accountServiceImplement;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getAllPostByAccountId(String accountId) {

        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("Account with " + accountId + "doesn't exists"));
        List<Post> postList = postRepo.findAllPostByAccount(account);

        return postList.stream()
                .map(postEntity -> modelMapper.map(postEntity , PostDTO.class))
                .toList();
    }

    @Override
    @Transactional
    public PostDTO createNewPost(PostDTO postDTO) {
        Account account = modelMapper.map(accountServiceImplement
                .findAccountById(postDTO.getAccount()) , Account.class);
        Post post = modelMapper.map(postDTO , Post.class);
        post.setPostDate(new Date());
        post.setId(account.getAccountName() + "-" + account.getEmail().substring(0 , 5) + "-" + UUID.randomUUID().toString().substring(0,5));

        Post savedPost = postRepo.save(post);
        account.getPosts().add(savedPost);
        accountRepo.save(account);

        return modelMapper.map(savedPost, PostDTO.class);
    }
}
