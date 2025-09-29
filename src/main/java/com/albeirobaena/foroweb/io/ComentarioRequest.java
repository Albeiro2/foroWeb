package com.albeirobaena.foroweb.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentarioRequest {

    private String id;
    private String comentario;
    private Integer like;
    private Integer noLike;
}
