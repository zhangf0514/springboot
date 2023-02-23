package com.orderfood.springboot.mapper;

import com.orderfood.springboot.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
public interface ShopMapper extends BaseMapper<Shop> {
    Boolean updateByIdEnable(Integer id, Boolean enable);
    Boolean updateByIdState(Integer id, Boolean state);

    Boolean updateByYiState(String address, Boolean state);
}
