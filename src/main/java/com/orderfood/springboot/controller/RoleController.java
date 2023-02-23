package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import  javax.annotation.Resource;
import java.util.List;

import  com.orderfood.springboot.service.IRoleService;
import  com.orderfood.springboot.entity.Role;

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
@RequestMapping("/role")
public class RoleController {

    @Resource
    private IRoleService roleService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Role role){
        return Result.success( roleService.saveOrUpdate(role));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(roleService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(roleService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(roleService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage( @RequestParam String name,
                            @RequestParam Integer pageNum,
                            @RequestParam Integer pageSize){
        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name",name);
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum,pageSize),queryWrapper)) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success( roleService.removeByIds(ids));
        }


}

