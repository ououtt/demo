package com.example.demo.web;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author guzemin@songxiaocai.com
 * @create 2018-03-18 19:56
 **/
@Controller
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/captcha-image")
    public ModelAndView getKaptchaImage(HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control",
                "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        System.out.println("capText: " + capText);

        try {
            String uuid = UUID.randomUUID().toString().trim();
            stringRedisTemplate.opsForValue().set(uuid, capText, 60 * 5, TimeUnit.SECONDS);
            Cookie cookie = new Cookie("captchaCode", uuid);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }


        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }
}
