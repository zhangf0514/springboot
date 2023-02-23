package com.orderfood.springboot.service;

import com.orderfood.springboot.entity.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
public interface IShopService extends IService<Shop> {
    Boolean updateByIdEnable(Integer id, Boolean enable);
    Boolean updateByIdState(Integer id, Boolean state);

    Boolean updateByYiState(String address, Boolean state);
}
