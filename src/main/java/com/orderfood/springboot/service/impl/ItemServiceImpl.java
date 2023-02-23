package com.orderfood.springboot.service.impl;

import com.orderfood.springboot.entity.Item;
import com.orderfood.springboot.mapper.ItemMapper;
import com.orderfood.springboot.service.IItemService;
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
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {
    @Resource
    private  ItemMapper itemMapper;
    public Integer findIdByItem(String itemname) {
        return itemMapper.findIdByItem(itemname);
    }
}
