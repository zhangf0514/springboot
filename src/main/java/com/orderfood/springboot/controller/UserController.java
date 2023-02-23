package com.orderfood.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orderfood.springboot.common.Constants;
import com.orderfood.springboot.common.Result;
import com.orderfood.springboot.controller.dto.UserDTO;
import com.orderfood.springboot.entity.User;
import com.orderfood.springboot.service.IUserService;
import com.orderfood.springboot.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhang
 * @since 2022-12-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;
    //新增或更新
    @PostMapping("/")
    public  Result save(@RequestBody User user){
        return Result.success(userService.saveOrUpdate(user));
        }
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody UserDTO userDTO){
        return Result.success(userService.updatePassword(userDTO));
    }
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return  Result.error(Constants.CODE_400,"参数错误");
        }
        UserDTO dto=userService.login(userDTO);
        return  Result.success(dto);
    }
    @PostMapping("/register")
    public  Result register(@RequestBody UserDTO userDTO){
        String username=userDTO.getUsername();
        String password=userDTO.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return  Result.error(Constants.CODE_400,"参数错误");
        }
        return Result.success(userService.register(userDTO));
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
        }

    @GetMapping("/")
    public Result findAll(){
        return Result.success(userService.list()) ;
        }
    @GetMapping("/username/{username}")
    public Result findOne(@PathVariable String username){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return Result.success( userService.getOne(queryWrapper));
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(userService.getById(id)) ;
        }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue ="") String username,
                               @RequestParam(defaultValue ="") String email,
                               @RequestParam(defaultValue ="") String address){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
       // queryWrapper.orderByDesc("id");
        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if (!"".equals(email)){
            queryWrapper.like("email",email);
        }
        if (!"".equals(address)){
            queryWrapper.like("address",address);
        }
        User currentUser= TokenUtils.getCurrentUser();
        return Result.success(userService.page(new Page<>(pageNum,pageSize),queryWrapper));
        }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        return Result.success(userService.removeByIds(ids)) ;
        }

    @GetMapping("/export")
    public void export(HttpServletResponse response )throws Exception{
        List<User> list =userService.list();
        ExcelWriter writer = ExcelUtil.getWriter(true);
        writer.addHeaderAlias("username","用户名");
        writer.addHeaderAlias("password","密码");
        writer.addHeaderAlias("nickname","昵称");
        writer.addHeaderAlias("email","邮箱");
        writer.addHeaderAlias("phone","电话");
        writer.addHeaderAlias("address","地址");
        writer.addHeaderAlias("createTime","创建时间");
        writer.addHeaderAlias("avatarurl","头像");
        writer.addHeaderAlias("enable","是否可用(0:不可用 1:可用)");
        writer.write(list,true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName= URLEncoder.encode("用户信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename="+fileName+".xlsx");

        ServletOutputStream out=response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();
        }

        @PostMapping("/import")
        public  Result imp(MultipartFile file )throws  Exception{
            InputStream inputStream=file.getInputStream();
            ExcelReader reader=ExcelUtil.getReader(inputStream);
            List<List<Object>> list=reader.read(1);
            List<User> users= CollUtil.newArrayList();
            for (List<Object> row:list){
                User user=new User();
                user.setUsername(row.get(0).toString());
                user.setPassword(row.get(1).toString());
                user.setNickname(row.get(2).toString());
                user.setEmail(row.get(3).toString());
                user.setPhone(row.get(4).toString());
                user.setAddress(row.get(5).toString());
                user.setAvatarurl(row.get(6).toString());
                user.setAvatarurl(row.get(7).toString());
                users.add(user);

            }
            userService.saveBatch(users);
            return Result.success(true);
        }

}

