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
 * @since 2023-02-13
 */
@Getter
@Setter
  @ApiModel(value = "Talk对象", description = "")
public class Talk implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("发起者id")
      private Integer first;

      @ApiModelProperty("接收者id")
      private Integer last;

      @ApiModelProperty("内容")
      private String content;
      @ApiModelProperty("聊天列表")
      private String chartid;
  @ApiModelProperty("谁发的")
  private String who;

}
