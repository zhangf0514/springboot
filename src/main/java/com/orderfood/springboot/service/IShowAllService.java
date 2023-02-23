package com.orderfood.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orderfood.springboot.entity.ShowAll;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2022-12-29
 */
public interface IShowAllService extends IService<Product> {
    Page<ShowAll> findPage(Page<ShowAll> page, String name,Integer itemid,Integer userid,Integer shopid);

    List<ShowAll> getProductByCustomerId(Integer id);
}
