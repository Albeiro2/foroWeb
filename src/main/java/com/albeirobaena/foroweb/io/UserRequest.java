package com.albeirobaena.foroweb.io;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String userName;
    private String email;
    private String password;
    private String name;
    private String description;
}
