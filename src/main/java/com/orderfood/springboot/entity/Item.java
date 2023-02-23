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
 * @since 2022-12-30
 */
@Getter
@Setter
  @ApiModel(value = "Item对象", description = "")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("itemid")
        @TableId(value = "itemid", type = IdType.AUTO)
      private Integer itemid;

      @ApiModelProperty("类别名称")
      private String itemname;


}
