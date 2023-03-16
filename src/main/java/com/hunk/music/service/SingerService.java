package com.hunk.music.service;

import com.github.pagehelper.PageInfo;
import com.hunk.music.domain.Singer;

import java.util.List;

/**
 * 歌手service接口
 */
public interface SingerService {

    /**
     * 增加
     */
    public boolean insert(Singer singer);

    /**
     * 修改
     */
    public boolean update(Singer singer);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    public Singer selectByPrimaryKey(Integer id);

    /**
     * 查询所有数据
     */
    public List<Singer> selectAll();

    /**
     * 根据歌手名字进行模糊查询
     */
    public List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     */
    public List<Singer> singerOfSex(Integer sex);

    /**
     * 分页
     */
    public PageInfo<Singer> findByPageService(int pageCode, int pageSize);
}
