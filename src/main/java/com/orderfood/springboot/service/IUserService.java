package com.orderfood.springboot.service;

import com.orderfood.springboot.controller.dto.UserDTO;
import com.orderfood.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2022-12-20
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);



    Integer updatePassword(UserDTO userDTO);
}
