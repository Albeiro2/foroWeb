package com.albeirobaena.foroweb.repository;

import com.albeirobaena.foroweb.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {

    List<PostEntity> findByUserPublicId(String userPublicId);
    Optional<PostEntity> findByPublicId(String publicId);
}
