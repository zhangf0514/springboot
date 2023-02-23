package com.orderfood.springboot.controller.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private Integer id;
    private  String customername;
    private  String password;
    private  String address;
    private  String phone;
    private  String avatarurl;
    private  String token;
}
