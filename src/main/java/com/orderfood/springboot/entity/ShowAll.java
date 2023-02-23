package com.orderfood.springboot.entity;

import lombok.Data;

@Data
public class ShowAll {
    private Integer id;
    private String name;
    private Float price;
    private Integer praise;
    private Integer number;
    private  String imageurl;
    private String  miaoshu;
    private String shopname;
    private String shopphone;
    private String shopaddress;
    private Integer state;
    private Integer itemid;
    private String itemname;
    private String username;
    private String avatarurl;
    private Integer carid;
    private Integer productid;
    private Integer shopid;
    private Integer customerid;
    private Integer buynumber;
    private boolean orderstate;
    private  Boolean isselect;
}
