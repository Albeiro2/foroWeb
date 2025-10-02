package com.albeirobaena.foroweb.service.impl;

import com.albeirobaena.foroweb.entity.PostEntity;
import com.albeirobaena.foroweb.io.PostRequest;
import com.albeirobaena.foroweb.io.PostResponse;
import com.albeirobaena.foroweb.repository.PostRepository;
import com.albeirobaena.foroweb.service.PostService;
import com.albeirobaena.foroweb.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    @Override
    public PostResponse uploadPost(PostRequest request) {
        PostEntity post = convertToEntity(request);
        var postSaved = postRepository.save(post);
        return  convertToResponse(postSaved);
    }

    private PostResponse convertToResponse(PostEntity post) {
        return  PostResponse.builder()
                .secondId(post.getSecondId())
                .userSecondId(post.getUserSecondId())
                .userName(post.getUserName())
                .post(post.getPost())
                .title(post.getTitle())
                .like(post.getLike())
                .noLike(post.getNoLike())
                .datePost(post.getDatePost())
                .reportCount(post.getReportCount())
                .comentarios(post.getComentarios())
                .build();
    }

    private PostEntity convertToEntity(PostRequest request) {
        return  PostEntity.builder()
                .userSecondId(userService.findByUserId())
                .secondId(UUID.randomUUID().toString())
                .userName(userService.findByUserName())
                .post(request.getPost())
                .datePost(LocalDateTime.now())
                .like(0)
                .noLike(0)
                .reportCount(0)
                .comentarios(new ArrayList<>())
                .title(request.getTitle())
                .build();
    }

    @Override
    public List<PostResponse> fetchAllPost() {
        return postRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> fetchAllPostOfUser(String userSecondId) {
        return postRepository.findByUserSecondId(userSecondId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> fetchAllPostOfUserLogged() {
        return postRepository.findByUserSecondId(userService.findByUserId()).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse findPost(String secondId) {
        PostEntity post = postRepository.findBySecondId(secondId)
                .orElseThrow(()-> new RuntimeException("Post no found"));
        return convertToResponse(post);
    }
}
