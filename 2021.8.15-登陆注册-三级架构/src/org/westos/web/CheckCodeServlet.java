package org.westos.web;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet(value = "/code", name = "CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int width=200;
        int length=70;
        BufferedImage img = new BufferedImage(width, length, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = img.getGraphics();
        graphics.setColor(Color.PINK);
        graphics.fillRect(0,0,width,length);
        graphics.setColor(Color.BLUE);
        graphics.drawRect(0,0,width-1,length-1);
        String code="";
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("宋体",Font.PLAIN,30));
        String str="0123456789abcdefghijklmnopqrsduvwxyzABCDEFGHIJKLMNOPQRSDUVWXYZ";
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(str.length());
            char c = str.charAt(index);
            code+=c;
            graphics.drawString(c+"",width/5*i,length/2);
        }
        request.getSession().setAttribute("check_code",code);
        graphics.setColor(Color.RED);
        for (int i = 0; i < 15; i++) {
            int x1=random.nextInt(width);
            int x2=random.nextInt(width);
            int y1=random.nextInt(length);
            int y2=random.nextInt(length);
            graphics.drawLine(x1,y1,x2,y2);
        }
        ImageIO.write(img,"jpg",response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
