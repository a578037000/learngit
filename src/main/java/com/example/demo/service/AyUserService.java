package com.example.demo.service;

import com.example.demo.AyUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

public interface AyUserService {

   AyUser findById(String id);

    List<AyUser> findAll();
    @Async
    Future<List<AyUser>> findAsynAll();
    AyUser save(AyUser ayUser);
    void delete(String id);
    Page<AyUser> findAll(Pageable pageable);
    List<AyUser> findByName(String name);
    List<AyUser> findByNameLike(String name);
    List<AyUser> findByIdIn(Collection<String> ids);
    //mybatis
    AyUser findByNameAndPassword( String name,String password);
    AyUser findByUserName( String name);

}
