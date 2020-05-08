package com.itfang.www.ui;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description: 用于对servlet共用的方法进行抽取
 * @Author: it-fang
 * @Date: 2019-04-29
 */
public class BaseServlet extends HttpServlet {
    /**
     * @Description: 用于对用户请求的方法进行分发与执行
     * @Param: [request, response]
     * @return: void
     * @Date: 2019-04-29
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 设置编码格式为UTF-8
        request.setCharacterEncoding("utf-8");
        // 获取请求的路径
        String uri = request.getRequestURI();
        // 获取请求的方法名称
        String requestName = uri.substring(uri.lastIndexOf('/')+ 1);
        try {
            // 获取方法的对象
            Method method = this.getClass().getMethod(requestName , HttpServletRequest.class , HttpServletResponse.class);
            // 执行这个方法
            Object invokeResponse = method.invoke(this, request, response);
            if(requestName.contains("queryTeacher")){
                request.getRequestDispatcher("/queryteacher.jsp").forward(request,response);
                return;
            }
            if (requestName.contains("toApply")){
                response.sendRedirect("/TeacherAppointmentSystem_war_exploded/application.jsp");
                return;
            }
            if(requestName.contains("queryApplication")){
                request.getRequestDispatcher("/queryapplication.jsp").forward(request,response);
                return;
            }
            if (requestName.contains("toAgree")){
                request.getRequestDispatcher("/agree.jsp").forward(request,response);
                return;
            }
            if (requestName.contains("toModify")){
                request.getRequestDispatcher("/modify.jsp").forward(request,response);
                return;
            }
            if (requestName.contains("queryRegister")){
                request.getRequestDispatcher("/queryregister.jsp").forward(request,response);
                return;
            }
            if (requestName.contains("studentEnterChatRoom") || requestName.contains("teacherEnterChatRoom")){
                response.sendRedirect("/TeacherAppointmentSystem_war_exploded/chatroom.jsp");
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            // 设置编码格式
            response.setContentType("application/json;charset=utf-8");
            // 将数据传回客户端
            objectMapper.writeValue( response.getOutputStream(), invokeResponse);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
