package com.orderfood.springboot.service;

import com.orderfood.springboot.entity.Chart;
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
public interface IChartService extends IService<Chart> {
    Integer findByAnswerAndQuestion(Integer answer,Integer question);
    void insert(Integer answer,Integer question);
    List<Chart> findPage(Integer question);
    List<Chart> findAnswer(Integer answer);
    Integer deleteByCustomerId(Integer customerid);
}
