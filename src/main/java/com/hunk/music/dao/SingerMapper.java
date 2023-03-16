package com.hunk.music.dao;

import com.hunk.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手管理dao
 */
@Repository
public interface SingerMapper {
    /**
     * 增加
     */
    public int insert(Singer singer);

    /**
     * 修改
     */
    public int update(Singer singer);

    /**
     * 删除
     */
    public int delete(Integer id);

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
    public List<Singer> findAllClass();
}
