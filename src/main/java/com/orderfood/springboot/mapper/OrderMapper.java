package com.orderfood.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.entity.Car;
import com.orderfood.springboot.entity.Order;
import com.orderfood.springboot.entity.OrderList;
import com.orderfood.springboot.entity.ShowAll;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2023-02-12
 */
public interface OrderMapper extends BaseMapper<Order> {

    Page<OrderList> findPage(Page<ShowAll> page,  Integer ordersid,String customername,String phone);
    void fast(Integer uid,Integer sid,Integer pid);
    List<OrderList> findOrderList(Integer id);
    List<Car> getSelectProduct(Integer id);
    void saveOrder(Integer uid,Integer pid,Integer sid);
}
