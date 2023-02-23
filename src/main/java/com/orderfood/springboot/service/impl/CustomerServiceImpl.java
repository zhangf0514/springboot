package com.orderfood.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orderfood.springboot.common.Constants;
import com.orderfood.springboot.controller.dto.CustomerDTO;
import com.orderfood.springboot.controller.dto.UserDTO;
import com.orderfood.springboot.entity.Customer;
import com.orderfood.springboot.entity.User;
import com.orderfood.springboot.exception.ServiceException;
import com.orderfood.springboot.mapper.CustomerMapper;
import com.orderfood.springboot.mapper.UserMapper;
import com.orderfood.springboot.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orderfood.springboot.utils.TokenUtils;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
    @Resource
    private CustomerMapper customerMapper;
    private  static  final Log LOG= Log.get();
    public CustomerDTO login(CustomerDTO customerDTO){
        Customer one=getCustomerInfo(customerDTO);
        if (one!=null){
            BeanUtil.copyProperties(one,customerDTO,true);
            String token= TokenUtils.genToken(one.getId().toString(),one.getPassword());
            customerDTO.setToken(token);
            return customerDTO;
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public Customer register(CustomerDTO customerDTO) {
       Customer one=getCustomerInfo(customerDTO);
        if(one==null){
            one=new Customer();
            BeanUtil.copyProperties(customerDTO,one,true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名已存在");
        }
        return one;
    }

    private  Customer getCustomerInfo(CustomerDTO customerDTO){
        QueryWrapper<Customer> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("customername",customerDTO.getCustomername());
        queryWrapper.eq("password",customerDTO.getPassword());
        Customer one;
        try{
            one=getOne(queryWrapper);
        }catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return  one;
    }
}
