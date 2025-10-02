package com.albeirobaena.foroweb.entity;

import com.albeirobaena.foroweb.io.ComentarioRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "posts")
public class PostEntity {

    @Id
    private String id;
    private String secondId;
    private String userSecondId;
    private String userName;
    private String title;
    private String post;
    private LocalDateTime datePost;
    private Integer like;
    private Integer noLike;
    private Integer reportCount;
    private List<ComentarioRequest> comentarios;

}
