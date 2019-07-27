package com.example.demo.listener;

import com.example.demo.AyUser;
import com.example.demo.service.AyUserService;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class AyUserListener implements ServletContextListener {
    @Resource
    private AyUserService ayUserService;
    @Override
    public  void contextInitialized(ServletContextEvent sce) {
        //sce.getServletContext();
        //通过name查询数据
        List<AyUser> localDics =ayUserService.findByName("阿毅");

        System.out.println("findByName()："+localDics.size());
        System.out.println("------------>>> listener init");
    }
    @Override
    public  void contextDestroyed(ServletContextEvent sce) {
        System.out.println("------------>>> listener destroyed");
    }
}
