package com.orderfood.springboot.service;

import com.orderfood.springboot.entity.Yiqing;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2023-02-11
 */
public interface IYiqingService extends IService<Yiqing> {

    Integer updateNumber(Yiqing yiqing);
}
