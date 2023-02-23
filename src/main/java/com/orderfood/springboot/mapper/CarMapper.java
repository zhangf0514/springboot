package com.orderfood.springboot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.orderfood.springboot.entity.Order;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2023-01-02
 */
public interface CarMapper extends BaseMapper<Car> {

    Page<Order> findpage(Page<Object> objectPage, String customername, String shopname);

    Boolean updateNumber(Integer customerid, Integer productid, Integer buynumber);

    Boolean deleteCar(Integer customerid, Integer carid);

    Integer deleteByCustomerId(Integer customerid);

    Boolean changeSelect(Integer customerid, Integer carid);
    Boolean  deleteSelect(Integer customerid);
}
