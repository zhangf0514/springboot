package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import org.springframework.web.bind.annotation.*;
import  javax.annotation.Resource;
import java.util.List;

import  com.orderfood.springboot.service.ITalkService;
import  com.orderfood.springboot.entity.Talk;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-02-13
 */
@RestController
@RequestMapping("/talk")
public class TalkController {

    @Resource
    private ITalkService talkService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Talk talk){
        return Result.success( talkService.saveOrUpdate(talk));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(talkService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(talkService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(talkService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer first,
                            @RequestParam Integer last){
        return Result.success(talkService.page(first,last)) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  talkService.removeByIds(ids));
        }


}

