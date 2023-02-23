package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import  javax.annotation.Resource;
import java.util.List;

import  com.orderfood.springboot.service.IItemService;
import  com.orderfood.springboot.entity.Item;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2022-12-30
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Resource
    private IItemService itemService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Item item){
        return Result.success( itemService.saveOrUpdate(item));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(itemService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(itemService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(itemService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize){
        QueryWrapper<Item> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(itemService.page(new Page<>(pageNum,pageSize),queryWrapper)) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  itemService.removeByIds(ids));
        }


}

