package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import com.orderfood.springboot.entity.Car;
import com.orderfood.springboot.entity.Order;
import com.orderfood.springboot.entity.OrderList;
import com.orderfood.springboot.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2023-02-12
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private IOrderService orderService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Order order){
        return Result.success( orderService.saveOrUpdate(order));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(orderService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(orderService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(orderService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                           @RequestParam Integer ordersid,
                           @RequestParam(defaultValue ="")  String customername,
                           @RequestParam(defaultValue ="")  String phone
                           ){
        if (pageNum==null){
            pageNum=1;
        }
        Page<OrderList> page= orderService.findPage(new Page<>(pageNum,pageSize),ordersid,customername,phone);
        return Result.success(page) ;
        }
    @GetMapping("/orderList")
    public  Result findOrderList(@RequestParam Integer id){
        return  Result.success(orderService.findOrderList(id));
    }
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success( orderService.removeByIds(ids));
    }
    @GetMapping("/order/{id}")
    public Result order(@PathVariable Integer id){
        List<Car> car= (List<Car>) orderService.getSelectProduct(id);
        for (Car a:car){
            Order order=new Order();
            order.setOrderuid(id);
            order.setOrderpid(a.getProductid());
            order.setOrdersid(a.getShopid());
            orderService.saveOrder(order);
        }
        return Result.success(car.size());
    }
    @GetMapping("/fast")
    public void fast(@RequestParam Integer uid,
                       @RequestParam Integer sid,
                       @RequestParam Integer pid
                       ){
       orderService.fast(uid,sid,pid);
    }


}

