package com.orderfood.springboot.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.orderfood.springboot.common.Constants;
import com.orderfood.springboot.entity.User;
import com.orderfood.springboot.exception.ServiceException;
import com.orderfood.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    private IUserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token=request.getHeader("token");
        if (!(handler instanceof HandlerMethod)){
            return  true;
        }
        if (StrUtil.isBlank(token)){
            throw new ServiceException(Constants.CODE_401,"无token,请重新登录");
        }
        String userId;
        try {
            userId= JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException j){
            throw  new ServiceException(Constants.CODE_401,"token验证失败");
        }
        User user=userService.getById(userId);
        if (user==null){
            throw  new ServiceException(Constants.CODE_401,"用户不存在，请重新登录");
        }
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw  new ServiceException(Constants.CODE_401,"token验证失败，请重新登录");
        }
        return true;
    }
}
