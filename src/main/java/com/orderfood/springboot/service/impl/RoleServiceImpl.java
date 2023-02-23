package com.orderfood.springboot.service.impl;

import com.orderfood.springboot.entity.Role;
import com.orderfood.springboot.mapper.RoleMapper;
import com.orderfood.springboot.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2022-12-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
