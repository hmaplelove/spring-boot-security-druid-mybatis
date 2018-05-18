package com.mvwchina.cloud.api.web.controller;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvwchina.cloud.api.common.utils.VerifyUtil;

@RestController
public class CommonController {

	@RequestMapping("/valicode")  
    public void valicode(HttpServletResponse response,HttpSession session) throws Exception{    
        //利用图片工具生成图片    
        //第一个参数是生成的验证码，第二个参数是生成的图片    
        Object[] objs = VerifyUtil.createImage();    
        //将验证码存入Session    
        session.setAttribute("imageCode",objs[0]);
        //将图片输出给浏览器    
        BufferedImage image = (BufferedImage) objs[1];    
        response.setHeader("Cache-Control", "no-store");    
        response.setHeader("Pragma", "no-cache");    
        response.setDateHeader("Expires", 0);  
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();    
        ImageIO.write(image, "png", os);  
    }  
}
