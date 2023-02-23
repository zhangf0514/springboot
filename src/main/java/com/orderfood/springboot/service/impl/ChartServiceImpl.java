package com.orderfood.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.orderfood.springboot.entity.Chart;
import com.orderfood.springboot.mapper.ChartMapper;
import com.orderfood.springboot.service.IChartService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2023-02-13
 */
@Service
public class ChartServiceImpl extends ServiceImpl<ChartMapper, Chart> implements IChartService {
    @Resource
    private ChartMapper chartMapper;
   public Integer findByAnswerAndQuestion(Integer answer,Integer question) {
       return chartMapper.findByAnswerAndQuestion(answer,question);
   }
   public void insert(Integer answer,Integer question) {
       chartMapper.insert(answer,question);
   }
   public List<Chart> findPage(Integer question) {
    return chartMapper.findPage(question);
    }
    public List<Chart> findAnswer(Integer answer) {
        return chartMapper.findAnswer(answer);
    }
    @Override
    public Integer deleteByCustomerId(Integer customerid) {
        return chartMapper.deleteByCustomerId(customerid);
    }

}
