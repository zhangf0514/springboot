package com.orderfood.springboot.mapper;

import com.orderfood.springboot.entity.Yiqing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2023-02-11
 */
public interface YiqingMapper extends BaseMapper<Yiqing> {

    Integer updateNumber(String address,Integer number);
}
