package com.orderfood.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhang
 * @since 2022-12-29
 */
@Getter
@Setter
  @ApiModel(value = "Product对象", description = "")
public class Product implements  Serializable  {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品名称")
      private String name;

      @ApiModelProperty("价格")
      private Float price;

      @ApiModelProperty("点赞量")
      private Integer praise;

      @ApiModelProperty("销量")
      private Integer number;

      @ApiModelProperty("图片地址")
      private String imageurl;

      @ApiModelProperty("商品描述")
      private String miaoshu;

      @ApiModelProperty("分类id")
      private Integer itemid;

      @ApiModelProperty("商家id")
      private Integer userid;
      @ApiModelProperty("商店id")
      private Integer shopid;

}
