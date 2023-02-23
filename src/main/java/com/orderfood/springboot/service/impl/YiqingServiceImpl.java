package com.orderfood.springboot.service.impl;

import com.orderfood.springboot.entity.Yiqing;
import com.orderfood.springboot.mapper.UserMapper;
import com.orderfood.springboot.mapper.YiqingMapper;
import com.orderfood.springboot.service.IYiqingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2023-02-11
 */
@Service
public class YiqingServiceImpl extends ServiceImpl<YiqingMapper, Yiqing> implements IYiqingService {
    @Resource
    private YiqingMapper yiqingMapper;
   public  Integer updateNumber(Yiqing yiqing) {
       String address=yiqing.getYiqingaddress();
       Integer number=yiqing.getYiqingnumber();
    return yiqingMapper.updateNumber(address,number);
    }
}
