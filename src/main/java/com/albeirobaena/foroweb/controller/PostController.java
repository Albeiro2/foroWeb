package com.albeirobaena.foroweb.controller;

import com.albeirobaena.foroweb.io.PostRequest;
import com.albeirobaena.foroweb.io.PostResponse;
import com.albeirobaena.foroweb.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse uploadPost(@RequestBody PostRequest request){
        return postService.uploadPost(request);
    }

    @GetMapping("/all")
    public List<PostResponse> fetchAllPost(){
        return postService.fetchAllPost();
    }

    @GetMapping("/{userSecondId}")
    public List<PostResponse> fetchPostsUser(@PathVariable String userSecondId){
        return postService.fetchAllPostOfUser(userSecondId);
    }
    @GetMapping("/logged")
    public List<PostResponse> fetchPostsUserLogged(){
        return  postService.fetchAllPostOfUserLogged();
    }

    @GetMapping("/find/{secondId}")
    public PostResponse findPost(@PathVariable String secondId){
        return postService.findPost(secondId);
    }
}
