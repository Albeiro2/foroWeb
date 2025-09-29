package com.albeirobaena.foroweb.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private String userId;
    private String userName;
    private String title;
    private String post;
}
