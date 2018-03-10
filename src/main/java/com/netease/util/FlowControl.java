package com.netease.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.*;

public class FlowControl extends HandlerInterceptorAdapter {
    private int permits;

    private int timeout;

    private int bufferSize;

    private String url;

    private final Semaphore semaphore;

    private BlockingQueue blockingQueue;

    public FlowControl(int permits, int timeout, int bufferSize, String url) {
        this.permits = permits;
        this.timeout = timeout;
        this.bufferSize = bufferSize;
        this.url = url;
        semaphore = new Semaphore(permits);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, InterruptedException {

            if (semaphore.tryAcquire(timeout, TimeUnit.MILLISECONDS)) {
                System.out.println("request was permitted");
                Thread.sleep((int)(Math.random()*1000));
                semaphore.release();
                return true;
            } else {
                System.out.println("request was overload");
                Thread.sleep(100);
                response.sendRedirect(request.getContextPath()+url);
                return false;
            }


    }
}
