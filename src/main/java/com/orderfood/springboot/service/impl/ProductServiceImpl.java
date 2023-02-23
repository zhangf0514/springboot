package com.orderfood.springboot.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.entity.Product;
import com.orderfood.springboot.entity.ShowAll;
import com.orderfood.springboot.mapper.ShowAllMapper;
import com.orderfood.springboot.service.IShowAllService;
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
 * @since 2022-12-29
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ShowAllMapper, Product> implements IShowAllService {
 @Resource
 private ShowAllMapper productMapper;
   public List<ShowAll> getProductByCustomerId(Integer id){
        return productMapper.getProductByCustomerId(id);
    };
    @Override
    public Page<ShowAll> findPage(Page<ShowAll> page, String name,Integer itemid,Integer userid,Integer shopid) {
        return productMapper.findPage(page,name,itemid,userid,shopid);
    }
}
