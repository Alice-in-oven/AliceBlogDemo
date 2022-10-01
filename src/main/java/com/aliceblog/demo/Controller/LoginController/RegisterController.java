package com.aliceblog.demo.Controller.LoginController;

import com.aliceblog.demo.Service.EmailService;
import com.aliceblog.demo.common.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/register")
@Api(value = "注册用接口集合")
public class RegisterController {
    @Autowired
    EmailService emailService;

    @GetMapping("/getCode")
    @ApiOperation("获取邮箱验证码")
    public R getEmailCode(@RequestParam(value = "email",required = true) String email, HttpSession session){

        String code = emailService.email(email);
        session.setAttribute("emailCode",code);
        return R.ok().data("code",code);
    }
    @GetMapping("/register")
    @ApiOperation("注册接口")
    public R register(@RequestParam("username")String username){


        return R.ok();
    }
}
