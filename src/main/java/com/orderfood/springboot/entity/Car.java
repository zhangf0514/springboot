package com.orderfood.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2023-01-02
 */
@Getter
@Setter
  @ApiModel(value = "Car对象", description = "")
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "carid", type = IdType.AUTO)
      private Integer carid;

      @ApiModelProperty("商品id")
      private Integer productid;

      @ApiModelProperty("商家id")
      private Integer shopid;

      @ApiModelProperty("消费者id")
      private Integer customerid;

      @ApiModelProperty("数量")
      private Integer buynumber;
      @ApiModelProperty("是否选中)")
      private Boolean  isselect;

}
