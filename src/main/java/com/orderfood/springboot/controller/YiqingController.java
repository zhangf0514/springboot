package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import  javax.annotation.Resource;
import java.util.List;

import  com.orderfood.springboot.service.IYiqingService;
import  com.orderfood.springboot.entity.Yiqing;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-02-11
 */
@RestController
@RequestMapping("/yiqing")
public class YiqingController {

    @Resource
    private IYiqingService yiqingService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Yiqing yiqing){
        QueryWrapper<Yiqing> queryWrapper=new QueryWrapper<>();
        if (!"".equals(yiqing.getYiqingaddress())){
            queryWrapper.like("yiqingaddress",yiqing.getYiqingaddress());
        }
       if(yiqingService.page(new Page<>(1,5),queryWrapper).getTotal()==0) {
           return Result.success( yiqingService.saveOrUpdate(yiqing));
       }else{
           return Result.success( yiqingService.updateNumber(yiqing));
       }

        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(yiqingService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(yiqingService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(yiqingService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue ="") String yiqingaddress){
        QueryWrapper<Yiqing> queryWrapper=new QueryWrapper<>();
        if (!"".equals(yiqingaddress)){
            queryWrapper.like("yiqingaddress",yiqingaddress);
        }
        queryWrapper.orderByDesc("id");
        return Result.success(yiqingService.page(new Page<>(pageNum,pageSize),queryWrapper)) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  yiqingService.removeByIds(ids));
        }


}

