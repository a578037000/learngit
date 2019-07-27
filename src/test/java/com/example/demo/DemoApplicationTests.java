package com.example.demo;

import com.example.demo.redis.RedisUtil;
import com.example.demo.service.AyUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private AyUserService ayUserService;
    @Resource
    private    RedisUtil redisUtil;
    @Test
    public void contextLoads() {
        List<Map<String,Object>> result = jdbcTemplate.queryForList("select * from student");
        System.out.println("query result is" + result.size());
        System.out.println("success");

        /*************************/
        List<AyUser> userList1=ayUserService.findAll();
        System.out.println("findAll():"+userList1.size());
        //通过name查询数据
        List<AyUser> userList2 =ayUserService.findByName("阿毅");
        System.out.println("findByName()："+userList2.size());
        Assert.isTrue(userList2.get(0).getName().equals("阿毅"),"data error");
        //通过name模糊查询
        List<AyUser> userList3 =ayUserService.findByNameLike("%阿%");
        System.out.println("findByNameLike()："+userList3.size());
        Assert.isTrue(userList3.get(0).getName().equals("阿毅"),"data error");
        //通过id列表查询数据
        List<String> ids=new ArrayList<>();
        ids.add("1");
        ids.add("2");
        ids.add("3");
        List<AyUser> userList4 =ayUserService.findByIdIn(ids);
        System.out.println("findByIdIn()："+userList4.size());
        //分页查询数据
        PageRequest pageRequest =new PageRequest(0,10);
        Page<AyUser> userList5 =ayUserService.findAll(pageRequest);
        System.out.println("page findAll()："+userList5.toString());
        //新增数据
        AyUser ayUser =new AyUser();
        ayUser.setPassword("6666");
        ayUser.setName("王二麻");
        ayUser.setId("10");
        ayUserService.save(ayUser);
        //删除数据
        ayUserService.delete("3");
        boolean isok=  redisUtil.set("name","张三6666");
        System.out.println(isok);
    }

}
