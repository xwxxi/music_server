package com.hunk.music.service;

import com.hunk.music.domain.SongList;

import java.util.List;

/**
 * 歌单service接口
 */
public interface SongListService {
    /**
     * 增加
     */
    public boolean insert(SongList songList);

    /**
     * 修改
     */
    public boolean update(SongList songList);

    /**
     * 删除
     */
    public boolean delete(Integer id);

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
