package com.orderfood.springboot.mapper;

import com.orderfood.springboot.controller.dto.UserDTO;
import com.orderfood.springboot.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2022-12-20
 */
public interface UserMapper extends BaseMapper<User> {
    Integer updatePassword(Integer id ,String password);
}
