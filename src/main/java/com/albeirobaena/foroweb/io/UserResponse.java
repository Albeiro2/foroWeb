package com.albeirobaena.foroweb.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String publicId;
    private String userName;
    private String email;
    private String description;
    private String name;
}
