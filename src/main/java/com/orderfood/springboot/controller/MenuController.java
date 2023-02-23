package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import  javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import  com.orderfood.springboot.service.IMenuService;
import  com.orderfood.springboot.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2022-12-26
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Menu menu){
        return Result.success( menuService.saveOrUpdate(menu));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(menuService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(@RequestParam(defaultValue = "") String name){
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);
        List<Menu> list=menuService.list(queryWrapper);
        List<Menu> parentNode = list.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());
        for(Menu menu:parentNode){
           menu.setChildren(list.stream().filter(m -> menu.getId().equals(m.getPid())).collect(Collectors.toList()));
        }
        return  Result.success(parentNode);
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(menuService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam String name,
                            @RequestParam Integer pageNum,
                            @RequestParam Integer pageSize){
        QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(menuService.page(new Page<>(pageNum,pageSize),queryWrapper)) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  menuService.removeByIds(ids));
        }


}

