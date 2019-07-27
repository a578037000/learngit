package com.example.demo.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(filterName =  "ayUserFilter",urlPatterns = "/*")
public class AyUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("------------>>> filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 判断是否登录，访问系统.do必须登录后才能访问
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url=  req.getRequestURI();
        HttpSession session=req.getSession();
        if(url.startsWith("unLogin")){
            chain.doFilter(request,response);
        }else if(session!=null){
            chain.doFilter(request,response);
        }else{
            resp.getWriter().print("error");
        }
        System.out.println("------------>>> doFilter url:"+url);
    }

    @Override
    public void destroy() {
        System.out.println("------------>>> filter destroy");
    }
}
