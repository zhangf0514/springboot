package com.orderfood.springboot.mapper;

import com.orderfood.springboot.entity.Chart;
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
public interface ChartMapper extends BaseMapper<Chart> {
     Integer findByAnswerAndQuestion(Integer answer,Integer question);
     void insert(Integer answer,Integer question);
     List<Chart> findPage(Integer question);
     List<Chart> findAnswer(Integer answer);
     Integer deleteByCustomerId(Integer customerid);
}
