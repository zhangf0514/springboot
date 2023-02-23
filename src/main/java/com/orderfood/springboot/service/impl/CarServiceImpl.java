package com.orderfood.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.entity.Car;
import com.orderfood.springboot.entity.Order;
import com.orderfood.springboot.mapper.CarMapper;
import com.orderfood.springboot.service.ICarService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2023-01-02
 */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements ICarService {
@Resource
private CarMapper carMapper;
    @Override
    public Page<Order> findpage(Page<Object> objectPage, String customername, String shopname) {

        return carMapper.findpage(objectPage,customername,shopname);
    }

    @Override
    public Boolean updateNumber(Integer customerid, Integer productid, Integer buynumber) {
        return carMapper.updateNumber( customerid, productid, buynumber);
    }

    @Override
    public Boolean deleteCar(Integer customerid, Integer carid) {
        return carMapper.deleteCar(customerid,carid);
    }

    @Override
    public Integer deleteByCustomerId(Integer customerid) {
        return carMapper.deleteByCustomerId(customerid);
    }

    @Override
    public Boolean changeSelect(Integer customerid, Integer carid) {
        return carMapper.changeSelect(customerid,carid);
    }
    public  Boolean  deleteSelect(Integer customerid){
        return carMapper.deleteSelect(customerid);
    };
}
