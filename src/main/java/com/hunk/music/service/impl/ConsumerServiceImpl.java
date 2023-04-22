package com.hunk.music.service.impl;

import com.hunk.music.dao.ConsumerMapper;
import com.hunk.music.domain.Consumer;
import com.hunk.music.service.ConsumerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 歌曲service接口实现类
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Resource
    private ConsumerMapper consumerMapper;

    /**
     * 增加
     *
     * @param consumer
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer) > 0;
    }

    /**
     * 修改
     *
     * @param consumer
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询
     *
     * @param id
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     */
    @Override
    public List<Consumer> selectAll() {
        return consumerMapper.selectAll();
    }

    /**
     * 根据账号名字进行模糊查询
     *
     * @param username
     */
    @Override
    public List<Consumer> consumerOfUserName(String username) {
        return consumerMapper.consumerOfUserName(username);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Consumer> consumerOfSex(Integer sex) {
        return null;
    }
}
