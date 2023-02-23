package com.orderfood.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
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
 * @since 2023-02-12
 */
@Getter
@Setter
  @ApiModel(value = "Order对象", description = "")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("用户ID")
      private Integer orderuid;

      @ApiModelProperty("店家ID")
      private Integer ordersid;

      @ApiModelProperty("商品ID")
      private Integer orderpid;

      @ApiModelProperty("订单状态(0:未完成 1：完成)")
      private Integer orderstate;

      @ApiModelProperty("订单创建的时间")
      private LocalDateTime ordercreatetime;


}
