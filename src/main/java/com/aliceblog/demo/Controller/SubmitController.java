package com.aliceblog.demo.Controller;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliceblog.demo.Dao.BlogMapper;
import com.aliceblog.demo.Dao.SpaceMapper;
import com.aliceblog.demo.Service.EmailService;
import com.aliceblog.demo.Service.ImgIoService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/submit")
public class SubmitController {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    SpaceMapper spaceMapper;
    @Autowired
    ImgIoService imgIoService;
    @Autowired
    EmailService emailService;

    @RequestMapping("/submitText")
    public String submit(@RequestParam("title")String title, @RequestParam("sub")String summary , @RequestParam("content")String content, @Param("taps")String taps){

        if(summary.equals("")){

            spaceMapper.submitSpace(title,content);
        }else {
            blogMapper.submitBlog(title,content,summary,taps);
        }
        return "index";
    }
 
    @RequestMapping("/submitFile")
    @ResponseBody
    public String submitFile( @RequestParam(value = "editormd-image-file", required = false) MultipartFile attach){

        String fileName = RandomUtil.randomString(10)+".jpg";

        imgIoService.UpdatePortrait(attach,fileName);

        JSONObject object = new JSONObject();
        object.append("success",1);
        object.append("message","上传成功");
        object.append("url","/blogimg/"+fileName);

        return JSONUtil.toJsonStr(object);

    }
    @RequestMapping("/submitCode")
    @ResponseBody
    public String submitCode (@RequestParam("code") String code, HttpSession session){
        String requiredCode = (String)session.getAttribute("requiredCode");
//        System.out.println("接受的code:"+code);

        if(code.equals(requiredCode)){
            return "success";
        }else{
            return "fail";
        }


    }
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode (HttpSession session){
        session.setAttribute("code","success");
        return "success";
    }
}
