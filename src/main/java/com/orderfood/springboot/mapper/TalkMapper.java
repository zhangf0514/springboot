package com.orderfood.springboot.mapper;

import com.orderfood.springboot.entity.Talk;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2023-02-13
 */
public interface TalkMapper extends BaseMapper<Talk> {
    List<Talk> page(Integer first, Integer last);
}
