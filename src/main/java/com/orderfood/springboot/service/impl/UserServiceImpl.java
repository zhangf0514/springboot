package com.orderfood.springboot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.orderfood.springboot.common.Constants;
import com.orderfood.springboot.controller.dto.UserDTO;
import com.orderfood.springboot.entity.User;
import com.orderfood.springboot.exception.ServiceException;
import com.orderfood.springboot.mapper.UserMapper;
import com.orderfood.springboot.service.IUserService;
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
 * @since 2022-12-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
   @Resource
   private UserMapper userMapper;

    private  static  final Log LOG= Log.get();
public  UserDTO login(UserDTO userDTO){
    User one=getUserInfo(userDTO);
    if (one!=null){
        BeanUtil.copyProperties(one,userDTO,true);
        String token=TokenUtils.genToken(one.getId().toString(),one.getPassword());
        userDTO.setToken(token);
        return userDTO;
    }else {
        throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
    }
}

    @Override
    public User register(UserDTO userDTO) {
        User one=getUserInfo(userDTO);
        if(one==null){
            one=new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);
        }else {
            throw new ServiceException(Constants.CODE_600,"用户名已存在");
        }
        return one;
    }



    @Override
    public Integer updatePassword(UserDTO userDTO) {
        Integer id=userDTO.getId();
        String password=userDTO.getPassword();

    return userMapper.updatePassword(id,password);
    }

    private  User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
            one=getOne(queryWrapper);
        }catch (Exception e) {
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }
        return  one;
    }
}
