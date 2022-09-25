package com.aliceblog.demo.Service;

import cn.hutool.core.util.RandomUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class ImgIoService {


    public void UpdatePortrait(MultipartFile file, String filename){

        String path="/BlogResources/blogimg/";

        try {
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
