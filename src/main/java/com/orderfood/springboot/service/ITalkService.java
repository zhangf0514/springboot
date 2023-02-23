package com.orderfood.springboot.service;

import com.orderfood.springboot.entity.Chart;
import com.orderfood.springboot.entity.Talk;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2023-02-13
 */
public interface ITalkService extends IService<Talk> {
List<Talk> page(Integer first,Integer last);
}
