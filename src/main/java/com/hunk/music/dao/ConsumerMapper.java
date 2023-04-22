package com.hunk.music.dao;

import com.hunk.music.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户管理dao
 */
@Repository
public interface ConsumerMapper {
    /**
     * 增加
     */
    public int insert(Consumer consumer);

    /**
     * 修改
     */
    public int update(Consumer consumer);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询
     */
    public Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有数据
     */
    public List<Consumer> selectAll();

    /**
     * 根据账号名字进行模糊查询
     */
    public List<Consumer> consumerOfUserName(String username);

    /**
     * 根据性别查询
     */
    public List<Consumer> consumerOfSex(Integer sex);
}
