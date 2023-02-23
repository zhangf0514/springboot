package com.orderfood.springboot.mapper;

import com.orderfood.springboot.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
public interface ItemMapper extends BaseMapper<Item> {
    Integer findIdByItem(@Param("itemname") String itemname);
}
