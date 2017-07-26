package com.insigma.mvc.controller.common.codevalidator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.insigma.mvc.MvcHelper;
import com.insigma.resolver.AppException;

/**
 * Created by Administrator on 2015-01-14.
 */
@Controller
@RequestMapping(value = "/verifycode")
public class CodeValidatorController extends MvcHelper {


    Log log = LogFactory.getLog(CodeValidatorController.class);

    /**
     * 验证码生成
     *
     * @param request
     * @param response
     * @return
     * @throws com.insigma.resolver.AppException
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/create")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws AppException {
        //回应为image
        response.setContentType("image/jpeg");
        // 设置要画的图片的宽和高
        int width = 70;
        int height = 24;
        // 具有可访问图像数据缓冲区的Image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 创建两个画笔
        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(92,180,230));// 上色
        g.fillRect(0, 0, width, height);// 全图绘制背景色
        // 在图片上绘制150条干扰线
        //g.setColor(getColor(180, 200));// 上色
        Random random = new Random();
		/*for (int i = 0; i < 150; i++) {
			// 绘制线的坐标
			int x = random.nextInt(width - 1);
			int y = random.nextInt(height - 1);
			// x、y坐标偏移多少个单位
			int deltax = random.nextInt(6) + 1;
			int deltay = random.nextInt(12) + 1;
			// 构造一个具有指定属性的实心的 BasicStroke（2D图形）
			BasicStroke bs = new BasicStroke(2f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL);
			// Line2D是一个无法直接实例化的抽象类
			Line2D line = new Line2D.Double(x, y, x + deltax, y + deltay);
			g2d.setStroke(bs);// 绘制 BasicStroke
			g2d.draw(line);// 再绘制线条
		}*/

        // 输出那些字符,以便验证
        String str = "";
        int temp = 0;
        Font font = new Font("Tahoma", Font.PLAIN, 16);
        g.setFont(font);// 设置画笔画的字体格式
        // 绘制四个字符
        for (int i = 0; i < 4; i++) {
            //if (random.nextInt(2) == 1) {
            temp = random.nextInt(26) + 'a';
			/*} else {
				temp = random.nextInt(10) + '0';
			}*/
            char ctemp = (char) temp;
            str += ctemp;
			/*Color c = getColor(20, 130);//颜色较深
			g.setColor(c);
			*//** ******随机将文字缩放并旋转一定的角度****** *//*
			AffineTransform trans = new AffineTransform();
			//45弧度，旋转点位置
			trans.rotate(random.nextInt(45) * Math.PI / 180, 15 * i + 10, 6);
			// 缩放文字
			float scaleSize = random.nextFloat() + 0.5f;//缩放大小
			if (scaleSize < 0.8f || scaleSize > 1.2) {
				scaleSize = 1.0f;
			}
			trans.scale(scaleSize, scaleSize);
			g2d.setTransform(trans);
			//画在什么位置
			g.drawString(ctemp + "", 15 * i + 10, 14);*/

        }
        //Color c = getColor(20, 130);//颜色较深
        g.setColor(Color.WHITE);
        g.drawString(str, 16, 16);
        //禁止图片缓存那样刷新就不会有重复的图片出现
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "No-cache");
        response.setDateHeader("Expires", 0);
        
        SecurityUtils.getSubject().getSession().setAttribute("session_validator_code",str);
        try {
            //把得到的验证码保存称jpeg格式放在输入流中
            ImageIO.write(image, "jpeg", response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 验证码校验
     * @param request
     * @param response
     * @throws com.insigma.resolver.AppException
     */
    @RequestMapping(value = "/check")
    public void check(HttpServletRequest request ,HttpServletResponse response) throws  AppException{
        try{
        	String vaildatorCode =(String)request.getSession().getAttribute("session_validator_code");
            String verifycode = request.getParameter("verifycode");
            boolean result = false;
            // 验证码正确
            if (verifycode.equalsIgnoreCase(vaildatorCode)) {
                result = true;
               //清除session_validator_code
                request.getSession().removeAttribute("session_validator_code");
            }
            PrintWriter out = response.getWriter();
            out.print(result);
            out.flush();
            out.close();
        }catch (IOException e){
            throw new AppException(e);
        }
    }

    // 得到具有规定的颜色
    public static Color getColor(int start, int end) {
        Random R = new Random();
        int r = start + R.nextInt(end - start);
        int g = start + R.nextInt(end - start);
        int b = start + R.nextInt(end - start);
        return new Color(r, g, b);
    }

}
