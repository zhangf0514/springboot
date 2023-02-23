package com.orderfood.springboot.controller;

import com.orderfood.springboot.common.Result;
import com.orderfood.springboot.service.IChartService;
import com.orderfood.springboot.service.IUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-02-13
 */
@RestController
@RequestMapping("/chart")
public class ChartController {

    @Resource
    private IChartService chartService;
    @Resource
    private IUserService userService;
    //新增或更新
    @PostMapping("/")
    public  void save(@RequestParam Integer answer,@RequestParam Integer question){
       Integer len= chartService.findByAnswerAndQuestion(answer,question);
       if(len==0) {
           chartService.insert(answer, question);
       }
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(chartService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(chartService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(chartService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer question){
        return Result.success(chartService.findPage(question));
    }
    @GetMapping("/answer")
    public Result findAnswer(@RequestParam Integer answer){
        return Result.success(chartService.findAnswer(answer));
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success( chartService.removeByIds(ids));
        }
    @GetMapping("/del/delById")
    public Result deleteByCustomerId(@RequestParam Integer customerid){
        return Result.success( chartService.deleteByCustomerId(customerid));
    }

}

