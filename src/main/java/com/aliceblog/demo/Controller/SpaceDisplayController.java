package com.aliceblog.demo.Controller;


import cn.hutool.json.JSONUtil;
import com.aliceblog.demo.Dao.SpaceMapper;
import com.aliceblog.demo.POJO.SpaceArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/space")
public class SpaceDisplayController {

    @Autowired
    SpaceMapper spaceMapper;


    @RequestMapping("/pageDivide")
    public String pageDivide(@RequestParam("page")int page){
        Integer last = spaceMapper.acquireLastSpaceNum();
//        System.out.println(last);
        return JSONUtil.toJsonStr(spaceMapper.selectArticleByPage(page,last));
    }

    @RequestMapping("/sendAll")
    @ResponseBody
    public String sendAll(){
        return JSONUtil.toJsonStr(spaceMapper.selectAllPages());
    }



}
