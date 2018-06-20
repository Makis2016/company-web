package com.makis.base.servlet;

import com.makis.base.misc.CaptchaUtils;
import com.makis.base.misc.Tracker;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CaptchaServlet extends HttpServlet {
    public static final String KEY_CAPTCHA = "SE_KEY_MM_CODE";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 设置相应类型,告诉浏览器输出的内容为图片
        resp.setContentType("image/jpeg");
        // 不缓存此内容
        resp.setHeader("Pragma", "No-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expire", 0);

        try {
            HttpSession session = req.getSession();
            CaptchaUtils tool = new CaptchaUtils();
            StringBuffer code = new StringBuffer();
            BufferedImage image = tool.genRandomCodeImage(code);
            session.removeAttribute(KEY_CAPTCHA);
            session.setAttribute(KEY_CAPTCHA, code.toString());

            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", resp.getOutputStream());
        }
        catch (Exception e) {
            Tracker.error(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}