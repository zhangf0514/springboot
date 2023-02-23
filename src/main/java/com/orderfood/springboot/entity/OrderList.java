package com.orderfood.springboot.entity;

import lombok.Data;

@Data
public class OrderList {
private  Integer id;
private  Integer orderuid;
private String customername;
private String address;
private  String phone;
private  Integer ordersid;
private  String shopname;
private  String shopaddress;
private  Integer orderpid;
private String name;
private Integer price;
private  String imageurl;
private  String miaoshu;
private Boolean orderstate;
private String ordercreatetime;
}
