package com.orderfood.springboot.controller.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private  String username;
    private  String password;
    private  String role;
    private  String nickname;
    private  String avatarurl;
    private  String token;
}
