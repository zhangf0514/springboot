package com.orderfood.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orderfood.springboot.entity.ShowAll;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2022-12-29
 */
public interface ShowAllMapper extends BaseMapper<Product> {
    Page<ShowAll> findPageByName(Page<ShowAll> page, String name);

    Page<ShowAll> findPage(Page<ShowAll> page, String name,Integer itemid,Integer userid,Integer shopid);

    List<ShowAll> getProductByCustomerId(Integer id);
}
