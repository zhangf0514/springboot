package com.orderfood.springboot.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.orderfood.springboot.entity.User;
import com.orderfood.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class TokenUtils {

    private  static IUserService staticUserService;
    @Resource
    private  IUserService userService;
    @PostConstruct
    public void  setUserService(){
        staticUserService=userService;
    }
    public  static  String genToken(String userId,String sign){
       return JWT.create().withAudience(userId)
               .withExpiresAt(DateUtil.offsetHour(new Date(),2))
               .sign(Algorithm.HMAC256(sign));
    }
    //获取当前用户
    public  static User getCurrentUser(){
        try{
            HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token=request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String userId=JWT.decode(token).getAudience().get(0);
                return staticUserService.getById(Integer.valueOf(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
}
