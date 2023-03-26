package com.hunk.music.dao;

import com.hunk.music.domain.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单管理dao
 */
@Repository
public interface SongListMapper {
    /**
     * 增加
     */
    public int insert(SongList songList);

    /**
     * 修改
     */
    public int update(SongList songList);

    /**
     * 删除
     */
    public int delete(Integer id);

    /**
     * 根据主键查询
     */
    public SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有数据
     */
    public List<SongList> selectAll();

    /**
     * 根据风格查询
     */
    public List<SongList> selectByStyle(String style);

}
