package com.hunk.music.service;

import com.hunk.music.domain.Consumer;

import java.util.List;

/**
 * 用户管理service接口
 */
public interface ConsumerService {
    /**
     * 增加
     */
    public boolean insert(Consumer consumer);

    /**
     * 修改
     */
    public boolean update(Consumer consumer);

    /**
     * 删除
     */
    public boolean delete(Integer id);

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
