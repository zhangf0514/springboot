package com.orderfood.springboot.service.impl;

import com.orderfood.springboot.entity.Talk;
import com.orderfood.springboot.mapper.ChartMapper;
import com.orderfood.springboot.mapper.TalkMapper;
import com.orderfood.springboot.service.ITalkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class TalkServiceImpl extends ServiceImpl<TalkMapper, Talk> implements ITalkService {
    @Resource
    private TalkMapper talkMapper;
    public  List<Talk> page(Integer first, Integer last){
        return talkMapper.page(first,last);
    }
}
