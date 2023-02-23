package com.orderfood.springboot.service;

import com.orderfood.springboot.entity.Item;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
public interface IItemService extends IService<Item> {

    Integer findIdByItem(String itemname);
}
