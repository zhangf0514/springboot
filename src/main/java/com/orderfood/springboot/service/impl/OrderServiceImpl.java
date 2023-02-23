package com.orderfood.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.entity.Car;
import com.orderfood.springboot.entity.Order;
import com.orderfood.springboot.entity.OrderList;
import com.orderfood.springboot.entity.ShowAll;
import com.orderfood.springboot.mapper.OrderMapper;
import com.orderfood.springboot.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2023-02-12
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {
    @Resource
    private OrderMapper orderMapper;
    public Page<OrderList> findPage(Page<ShowAll> page,Integer ordersid,String customername,String phone) {
        return orderMapper.findPage(page,ordersid,customername,phone);
    }
    public void fast(Integer uid,Integer sid,Integer pid) {
    orderMapper.fast(uid,sid,pid);
    }
    public List<Car> getSelectProduct(Integer id) {
    return orderMapper.getSelectProduct(id);
    }
    public List<OrderList> findOrderList(Integer id) {
    return orderMapper.findOrderList(id);
    }
    public  void saveOrder(Order order){
       Integer uid=order.getOrderuid();
       Integer pid=order.getOrderpid();
       Integer sid=order.getOrdersid();
       System.out.println(uid);
       orderMapper.saveOrder(uid,pid,sid);
    }
}
