package com.netease.util;

import com.netease.pojo.User;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RoleInterceptorAdapter extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Permission permission = handlerMethod.getMethodAnnotation(Permission.class);
        Role[] roles = {Role.VISTOR};

        if (isValidate(roles, -1)) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (null != user && isValidate(roles, user.getRole())) {
            return true;
        }

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(403);
        OutputStream outputStream = response.getOutputStream();
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, "utf-8"));
        printWriter.println("{\"result\":false,\"code\":403,\"message\":\"Permission Denied.\"}");
        printWriter.flush();
        printWriter.close();

        return false;
    }

    private boolean isValidate(Role[] roles, int current) {

        for (Role role : roles) {
            if (role.getRole() == current) {
                return true;
            }
        }
        return false;
    }

//    public void postHandle(
//            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
//            throws Exception {
//    }
//    public void afterCompletion(
//            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//    }
}
