package com.orderfood.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Constants;
import com.orderfood.springboot.common.Result;
import com.orderfood.springboot.controller.dto.CustomerDTO;
import com.orderfood.springboot.entity.Customer;
import com.orderfood.springboot.service.ICustomerService;
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
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private ICustomerService customerService;

    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Customer customer){
        return Result.success( customerService.saveOrUpdate(customer));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(customerService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(customerService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(customerService.getById(id));
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                           @RequestParam(defaultValue ="") String username,
                           @RequestParam(defaultValue ="") String phone){
        QueryWrapper<Customer> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!"".equals(username)){
            queryWrapper.like("customername",username);
        }
        if (!"".equals(phone)){
            queryWrapper.like("phone",phone);
        }
        return Result.success(customerService.page(new Page<>(pageNum,pageSize),queryWrapper)) ;
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  customerService.removeByIds(ids));
        }

    @PostMapping("/login")
    public Result login(@RequestBody CustomerDTO customerDTO){
        String customername=customerDTO.getCustomername();
        String password=customerDTO.getPassword();
        if (StrUtil.isBlank(customername) || StrUtil.isBlank(password)){
            return  Result.error(Constants.CODE_400,"参数错误");
        }
        CustomerDTO dto=customerService.login(customerDTO);
        return  Result.success(dto);
    }

}

