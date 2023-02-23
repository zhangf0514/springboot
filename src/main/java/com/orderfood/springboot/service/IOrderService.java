package com.orderfood.springboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orderfood.springboot.entity.Car;
import com.orderfood.springboot.entity.Order;
import com.orderfood.springboot.entity.OrderList;
import com.orderfood.springboot.entity.ShowAll;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2023-02-12
 */
public interface IOrderService extends IService<Order> {
 Page<OrderList> findPage(Page<ShowAll> page,  Integer ordersid ,String customername,String phone);
 void fast(Integer uid,Integer sid,Integer pid);
 List<OrderList> findOrderList(Integer id);
 List<Car> getSelectProduct(Integer id);
 void saveOrder(Order order);
}
