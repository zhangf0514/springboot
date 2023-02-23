package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import com.orderfood.springboot.entity.Shop;
import com.orderfood.springboot.service.IShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private IShopService shopService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Shop shop){
        return Result.success( shopService.saveOrUpdate(shop));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(shopService.removeById(id));
        }
    @GetMapping("/updateByIdState")
    public Result updateByIdState(@RequestParam("id") Integer id,
                                   @RequestParam("state") Boolean state){
        return Result.success(shopService.updateByIdState(id,state));
    }
    @GetMapping("/updateByYiState")
    public Result updateByYiState(
                                    @RequestParam("address") String address,
                                    @RequestParam("state") Boolean state){
        System.out.println(address);
        System.out.println(state);
        return Result.success(shopService.updateByYiState(address,state));
    }
    @GetMapping("/updateByIdEnable")
    public Result updateByIdEnable(@RequestParam("id") Integer id,
                                   @RequestParam("enable") Boolean enable){
        return Result.success(shopService.updateByIdEnable(id,enable));
    }
    @GetMapping("/")
    public Result findAll(){
        return  Result.success(shopService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(shopService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue ="") String shopname,
                           @RequestParam(defaultValue ="") String shopphone,
                           @RequestParam(defaultValue ="") String shopaddress,
                           @RequestParam(defaultValue ="") String shopuserid
                           ) {
        QueryWrapper<Shop> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(shopname)){
            queryWrapper.like("shopname",shopname);
        }
        if (!"".equals(shopphone)){
            queryWrapper.like("shopphone",shopphone);
        }
        if (!"".equals(shopaddress)){
            queryWrapper.like("shopaddress",shopaddress);
        }
        if (!"".equals(shopuserid)){
            queryWrapper.eq("shopuserid",shopuserid);
        }
        return Result.success(shopService.page(new Page<>(pageNum,pageSize),queryWrapper)) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  shopService.removeByIds(ids));
        }


}

