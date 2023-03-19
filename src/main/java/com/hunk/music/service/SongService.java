package com.hunk.music.service;

import com.hunk.music.domain.Song;

import java.util.List;

/**
 * 歌曲service接口
 */
public interface SongService {

    /**
     * 增加
     */
    public boolean insert(Song song);

    /**
     * 修改
     */
    public boolean update(Song song);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    public Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有数据
     */
    public List<Song> selectAll();

    /**
     * 根据歌曲名字进行模糊查询
     */
    public List<Song> songOfName(String name);

    /**
     * 根据歌手id查询
     */
    public List<Song> songOfSex(Integer singerId);

}
