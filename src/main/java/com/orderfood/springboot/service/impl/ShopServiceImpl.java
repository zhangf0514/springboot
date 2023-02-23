package com.orderfood.springboot.service.impl;

import com.orderfood.springboot.entity.Shop;
import com.orderfood.springboot.mapper.ShopMapper;
import com.orderfood.springboot.mapper.UserMapper;
import com.orderfood.springboot.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {
    @Resource
    private ShopMapper shopMapper;
    public Boolean updateByIdState(Integer id, Boolean state) {
        return shopMapper.updateByIdState(id,state);
    }
    @Override
    public Boolean updateByIdEnable(Integer id, Boolean enable) {
        return shopMapper.updateByIdEnable(id,enable);
    }
    public Boolean updateByYiState(String address, Boolean state) {
        return shopMapper.updateByYiState(address,state);
    }

}
