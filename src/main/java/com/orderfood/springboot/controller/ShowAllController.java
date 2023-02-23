package com.orderfood.springboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Result;
import com.orderfood.springboot.entity.Product;
import com.orderfood.springboot.entity.ShowAll;
import com.orderfood.springboot.service.IItemService;
import com.orderfood.springboot.service.IShowAllService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2022-12-29
 */
@RestController
@RequestMapping("/product")
public class ShowAllController {

    @Resource
    private IShowAllService productService;
    @Resource
    private IItemService itemService;
    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody Product product){
        return Result.success( productService.saveOrUpdate(product));
        }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(productService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return  Result.success(productService.list());
        }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(productService.getById(id));
        }
    @GetMapping("/getProduct")
    public Result GetProductByCustomerId(@RequestParam Integer id){
        return Result.success(productService.getProductByCustomerId(id));
    }

   /* @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize){
        QueryWrapper<Product> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(productService.page(new Page<>(pageNum,pageSize),queryWrapper)) ;
        }
*/
    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(  productService.removeByIds(ids));
        }
    @GetMapping("/page")
    public Result findPage(@RequestParam(defaultValue ="") String name,
                           @RequestParam(defaultValue =" ") String searchitem,
                           @RequestParam(defaultValue =" ") Integer userid,
                           @RequestParam(defaultValue =" ") Integer shopid,
                            @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize){
        if (pageNum==null){
            pageNum=1;
        }
        Integer itemId;
        if (searchitem!="" && searchitem !=null){
            itemId =itemService.findIdByItem(searchitem);

        }else {
            itemId=null;
        }
        Page<ShowAll> page= productService.findPage(new Page<>(pageNum,pageSize),name,itemId,userid,shopid);
        return Result.success(page) ;
    }

}

