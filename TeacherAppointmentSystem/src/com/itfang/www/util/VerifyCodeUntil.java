package com.itfang.www.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author it-fang
 * 生成验证码的工具
 */
public class VerifyCodeUntil {
    private int width = 80;
    private int height = 40;
    private Random random = new Random();
    private String codes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private Color bgColor = new Color(200,200,200);
    private String text;

    /**
     * 生成随机的颜色
     * @return
     */
     private Color randomColor(){
         int red = random.nextInt(150);
         int green = random.nextInt(150);
         int blue = random.nextInt(150);
         return new Color(red,green,blue);
     }

    /**
     * 绘制随机位置的干扰线
     * @param image
     */
     private void drawLine(BufferedImage image){
         Graphics2D graphics2D = (Graphics2D) image.getGraphics();
         int num = 5;
         for (int i = 1; i <= num; i++) {
             int x1 = random.nextInt(width);
             int y1 = random.nextInt(height);
             int x2 = random.nextInt(width);
             int y2 = random.nextInt(height);
             //设置画笔宽度
             graphics2D.setStroke(new BasicStroke(0.5F));
             //设置干扰线的颜色
             graphics2D.setColor(randomColor());
             graphics2D.drawLine(x1,y1,x2,y2);
         }
     }

    /**
     * 生成一个随机字符
     * @return
     */
     private char randomChar(){
         int index = random.nextInt(codes.length());
         return codes.charAt(index);
     }

    /**
     * 创建图片缓冲流
     * @return
     */
     private BufferedImage creatImage(){
         BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
         Graphics2D graphics2D = (Graphics2D) image.getGraphics();
         graphics2D.setColor(this.bgColor);
         graphics2D.fillRect(0,0,width,height);
         return image;
     }

    /**
     * 获得验证码图片
     * @return
     */
     public BufferedImage getImage(){
         BufferedImage image = creatImage();
         Graphics2D graphics2D = (Graphics2D) image.getGraphics();
         StringBuilder sb = new StringBuilder();
         for (int i = 1; i <= 4 ; i++) {
             String randomChar = randomChar() + "";
             sb.append(randomChar);
             double x = i*1.0*width/6;
             graphics2D.setColor(randomColor());
             graphics2D.setFont(new Font(randomChar,3,25));
             graphics2D.drawString(randomChar,(int)x,height-10);
         }
         this.text = sb.toString();
         drawLine(image);
         return image;
     }

    /**
     * 获得验证码图片中的字符串
     * @return
     */
     public String getText(){
         return this.text;
     }

}
