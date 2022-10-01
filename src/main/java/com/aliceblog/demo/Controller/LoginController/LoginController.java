package com.aliceblog.demo.Controller.LoginController;

import com.aliceblog.demo.Service.EmailService;
import com.aliceblog.demo.common.R;
import com.aliceblog.demo.common.ResultCodeEnum;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("")
public class LoginController {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    EmailService emailService;

    @GetMapping("/getCode")
    @ApiOperation("获取邮箱验证码")
    public R getEmailCode(@RequestParam(value = "email",required = true) String email, HttpSession session){

        String code = emailService.email(email);
        redisTemplate.boundValueOps("emailCode").set(code);
        return R.ok().data("code",code);
    }

    @GetMapping("/login")
    public R login(@RequestParam(value = "email") String email,@RequestParam("code")String code,
                                  HttpSession session) {
        Subject userSubject = SecurityUtils.getSubject();
        Object emailCode = redisTemplate.boundValueOps("emailCode").get();
        UsernamePasswordToken token = new UsernamePasswordToken(email,code);
        try {
            userSubject.login(token);
            return R.ok();
        } catch (UnknownAccountException e){
            return R.error(ResultCodeEnum.ACCOUNT_NOT_EXIST);
        }catch (IncorrectCredentialsException e) {
            return R.error(ResultCodeEnum.INCORRECT_CREDENTIALS);
        } catch (Throwable e) {
            e.printStackTrace();
            return R.error(ResultCodeEnum.UNKNOWN_ERROR);
        }
    }



    @GetMapping("/auth")
    public String auth() {
        return "已成功登录";
    }
    @GetMapping("/interceptor")
    public String interceptor(){
        return "请先登录";
    }
    @GetMapping("/role")
    @RequiresRoles("vip")//筛选授权用户使用的方法需要使用这个注解,进入需要授权的方法前，Shiro才会给用户授权
    public String role() {
        return "测试Vip角色";
    }
    @GetMapping("/logout")
    public String logout(){
        Subject userSubject = SecurityUtils.getSubject();
        userSubject.logout();
        return "quit!";

    }

    public String permission() {
        return "测试Add和Update权限";
    }
}
