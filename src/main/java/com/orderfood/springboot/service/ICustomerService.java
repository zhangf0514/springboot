package com.orderfood.springboot.service;

import com.orderfood.springboot.controller.dto.CustomerDTO;
import com.orderfood.springboot.controller.dto.UserDTO;
import com.orderfood.springboot.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.orderfood.springboot.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2023-01-02
 */
public interface ICustomerService extends IService<Customer> {

    CustomerDTO login(CustomerDTO customerDTO);
    Customer register(CustomerDTO customerDTO);

}
