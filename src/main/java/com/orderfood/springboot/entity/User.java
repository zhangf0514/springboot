package com.orderfood.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2022-12-20
 */
@Getter
@Setter
  @ApiModel(value = "User对象", description = "")
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户名")
      private String username;

      @ApiModelProperty("密码")
      private String password;
      @ApiModelProperty("角色")
      private String role;
      @ApiModelProperty("昵称")
      private String nickname;

      @ApiModelProperty("邮箱")
      private String email;

      @ApiModelProperty("电话")
      private String phone;

      @ApiModelProperty("地址")
      private String address;

      @ApiModelProperty("创建时间")
      private Date createTime;

      @ApiModelProperty("头像")
      private String avatarurl;



}
