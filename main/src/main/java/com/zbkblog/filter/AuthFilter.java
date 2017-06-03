package com.zbkblog.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by zhangbokang on 2017/6/3.
 */
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("AuthFilterInfo");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
