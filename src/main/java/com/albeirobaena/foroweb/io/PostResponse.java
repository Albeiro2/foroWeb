package com.albeirobaena.foroweb.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private String publicId;
    private String userPublicId;
    private String userName;
    private String post;
    private String title;
    private LocalDateTime datePost;
    private Integer like;
    private Integer noLike;
    private Integer reportCount;
    private List<ComentarioRequest> comentarios;
}
