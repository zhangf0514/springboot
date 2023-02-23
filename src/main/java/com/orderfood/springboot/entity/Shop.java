package com.orderfood.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
@Getter
@Setter
  @ApiModel(value = "Shop对象", description = "")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("店名")
      private String shopname;

      @ApiModelProperty("电话")
      private String shopphone;

      @ApiModelProperty("地址")
      private String shopaddress;

      @ApiModelProperty("是否营业(0:未营业 1:营业)")
      private Boolean state;
      @ApiModelProperty("是否允许开店(0:不允许1:允许)")
      private Boolean enable;
      @ApiModelProperty("用户id")
      private Integer shopuserid;


}
