package com.albeirobaena.foroweb.service;

import com.albeirobaena.foroweb.io.PostRequest;
import com.albeirobaena.foroweb.io.PostResponse;

import java.util.List;

public interface PostService {

PostResponse uploadPost (PostRequest request);

List<PostResponse> fetchAllPost();

List<PostResponse> fetchAllPostOfUser(String userId);

List<PostResponse> fetchAllPostOfUserLogged();

PostResponse findPost(String publicId);
}
