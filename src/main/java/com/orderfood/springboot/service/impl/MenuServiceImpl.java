package com.orderfood.springboot.service.impl;

import com.orderfood.springboot.entity.Menu;
import com.orderfood.springboot.mapper.MenuMapper;
import com.orderfood.springboot.service.IMenuService;
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
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
