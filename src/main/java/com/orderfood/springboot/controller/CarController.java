package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import com.orderfood.springboot.entity.Car;
import com.orderfood.springboot.entity.Order;
import com.orderfood.springboot.service.ICarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-01-02
 */
@RestController
@RequestMapping("/car")
public class CarController {

    @Resource
    private ICarService carService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Car car){
        return Result.success( carService.saveOrUpdate(car));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(carService.removeById(id));
        }
    @GetMapping("/deleteCar")
    public Result deleteCar( @RequestParam Integer customerid,
                             @RequestParam Integer carid){
        return Result.success(carService.deleteCar(customerid,carid));
    }
    @GetMapping("/deleteSelect")
    public Result deleteSelect( @RequestParam Integer customerid){
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaa"+customerid);
        return Result.success(carService.deleteSelect(customerid));
    }
    @GetMapping("/")
    public Result findAll(){
        return  Result.success(carService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(carService.getById(id));
        }
    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue ="") String customername,
                           @RequestParam(defaultValue ="") String shopname,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize){
        Page<Order> page=carService.findpage(new Page<>(pageNum,pageSize),customername,shopname);
        return Result.success(page) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  carService.removeByIds(ids));
        }

    @GetMapping("/del/delById")
    public Result deleteByCustomerId(@RequestParam Integer customerid){
        return Result.success( carService.deleteByCustomerId(customerid));
    }
    @PostMapping("/changeSelect")
    public Result changeSelect(@RequestBody Car car){
        Integer customerid=car.getCustomerid();
        Integer carid=car.getCarid();
        return Result.success( carService.changeSelect(customerid,carid));
    }
    @PostMapping("/number")
    public Result updateNumber(@RequestParam Integer customerid,
                               @RequestParam Integer productid,
                               @RequestParam Integer buynumber){
        return Result.success( carService.updateNumber(customerid,productid,buynumber));
    }



}

