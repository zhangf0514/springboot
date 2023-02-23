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
 * @since 2023-02-11
 */
@Getter
@Setter
  @ApiModel(value = "Yiqing对象", description = "")
public class Yiqing implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("疫情爆发地")
      private String yiqingaddress;

      @ApiModelProperty("感染人数")
      private Integer yiqingnumber;


}
