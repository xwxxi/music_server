package com.hunk.music.service.impl;

import com.hunk.music.dao.SingerMapper;
import com.hunk.music.domain.Singer;
import com.hunk.music.service.SingerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 歌手service接口实现类
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Resource
    private SingerMapper singerMapper;


    /**
     * 增加
     *
     * @param singer
     */
    @Override
    public boolean insert(Singer singer) {
        return singerMapper.insert(singer) > 0;
    }

    /**
     * 修改
     *
     * @param singer
     */
    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return singerMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询
     *
     * @param id
     */
    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     */
    @Override
    public List<Singer> selectAll() {
        return singerMapper.selectAll();
    }

    /**
     * 根据歌手名字进行模糊查询
     *
     * @param name
     */
    @Override
    public List<Singer> singerOfName(String name) {
        return singerMapper.singerOfName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex
     */
    @Override
    public List<Singer> singerOfSex(Integer sex) {
        return singerMapper.singerOfSex(sex);
    }
}
