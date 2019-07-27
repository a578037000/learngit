package com.example.demo.repository;

import com.example.demo.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface AyUserRepository extends JpaRepository<AyUser,String> {
    /**
     * 描述 通过名字相等查询参数为name
     * @param name
     * @return
     */
    List<AyUser> findByName(String name);

    /**
     * 描述 通过名字查询参数为name
     * @param name
     * @return
     */
    List<AyUser> findByNameLike(String name);

    /**
     * 描述通过主键 id集合查询蚕食为ID、集合
     * @param ids
     * @return
     */
    List<AyUser> findByIdIn(Collection<String> ids);
}
