package com.example.demo;

import com.example.demo.ActiceMQbook.AyMood;
import com.example.demo.ActiceMQbook.AyMoodProducer;
import com.example.demo.ActiceMQbook.AyMoodService;
import com.example.demo.redis.RedisUtil;
import com.example.demo.service.AyUserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.jms.Destination;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAsync //异步执行
public class DemoApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Resource
    private AyUserService ayUserService;
    @Resource
    private AyMoodService ayMoodService;
    @Resource
    private AyMoodProducer ayMoodProducer;
    @Resource
    private    RedisUtil redisUtil;
    @Test
    public void contextLoads() {
        List<Map<String,Object>> result = jdbcTemplate.queryForList("select * from student");
        System.out.println("query result is" + result.size());
        System.out.println("success");
        //断言的使用
        //assert  1==2 :"程序错误";

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
        AyUser ayUser1 =   ayUserService.findByNameAndPassword("阿毅","1111");
        //Assert.notNull(ayUser1,"ayUser1 is null");
        AyUser ayUser2 =   ayUserService.findByUserName("阿王");
        //Assert.notNull(ayUser2,"ayUser2 is null");
    }
    @Test
    public void  testAyMood() {
        AyMood ayMood = new AyMood();
        ayMood.setId("1");
        ayMood.setUserId("1");
        ayMood.setPraiseNum(0);
        ayMood.setContent("这是我的第一条微信说说");
        AyMood mood = ayMoodService.save(ayMood);
        System.out.println(ayMood.getContent());
    }
    @Test
    public void testActiveMQ()
    {
        Destination destination = new ActiveMQQueue("ay.queue");
        ayMoodProducer.sendMessage(destination, "hello,mq!!!");
    }
    @Test
    public void testActiveMQAsynSave(){
        AyMood ayMood=new AyMood();
        ayMood.setId("6");
        ayMood.setUserId("6");
        ayMood.setPraiseNum(1);
        ayMood.setContent("异步保存消息6");
        ayMoodService.asynSave(ayMood);
    }
    @Test
    public void testAsync(){
        long startTime=System.currentTimeMillis();
        System.out.println("第一次查询");
        List<AyUser> ayUserList=ayUserService.findAll();

        System.out.println("第二次查询");
        List<AyUser> ayUserList2=ayUserService.findAll();

        System.out.println("第三次查询");
        List<AyUser> ayUserList3=ayUserService.findAll();

        long endTime=System.currentTimeMillis();
        System.out.println("总耗时"+(endTime-startTime)+"毫秒");
    }

    @Test
    public void testAsync2()throws Exception{
        long startTime = System.currentTimeMillis();
        System.out.println("第一次查询所有用户！");
        Future<List<AyUser>> ayUserList = ayUserService.findAsynAll();
        System.out.println("第二次查询所有用户！");
        Future<List<AyUser>> ayUserList2 = ayUserService.findAsynAll();
        System.out.println("第三次查询所有用户！");
        Future<List<AyUser>> ayUserList3 = ayUserService.findAsynAll();
        while (true){
            if(ayUserList.isDone() && ayUserList2.isDone() && ayUserList3.isDone()){
                break;
            }else {
                Thread.sleep(5);
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("总共消耗：" + (endTime - startTime) + "毫秒");
    }
}
