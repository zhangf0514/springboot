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
  @ApiModel(value = "Chart对象", description = "")
public class Chart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer question;
    private String customername;
    private String phone;
    private  String username;
    private  String avatarurl;
    private Integer answer;
    private Integer first;
    private Integer last;
    private  String  content;

      @ApiModelProperty("是否已读（0:未读 1:已读）")
      private Integer open;


}
