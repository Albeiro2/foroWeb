package com.albeirobaena.foroweb.repository;


import com.albeirobaena.foroweb.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUserName(String userName);
    Optional<UserEntity> findBySecondId(String secondId);
}
