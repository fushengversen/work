package com.netease.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {


            if (semaphore.tryAcquire()) {
                System.out.println("request was permitted");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("sleep exception");
                    response.sendRedirect(url);
                    response.setStatus(503);
                    return false;
                }
                semaphore.release();
                return true;
            } else {
                System.out.println("overload");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("sleep exception");
                    response.sendRedirect(url);
                    response.setStatus(503);
                    return false;
                }
                response.sendRedirect(url);
                response.setStatus(503);
                return false;


            }


    }
}
