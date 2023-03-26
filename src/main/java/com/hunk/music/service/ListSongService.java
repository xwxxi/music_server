package com.hunk.music.service;

import com.hunk.music.domain.ListSong;

import java.util.List;

/**
 * 歌单的歌曲service接口
 */
public interface ListSongService {
    /**
     * 增加
     */
    public boolean insert(ListSong listSong);

    /**
     * 修改
     */
    public boolean update(ListSong listSong);

    /**
     * 删除
     */
    public boolean delete(Integer id);

    /**
     * 根据主键查询
     */
    public ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有数据
     */
    public List<ListSong> selectAll();

    /**
     * 根据歌单id查询所有的歌曲
     */
    public List<ListSong> getSongBySongListId(Integer songListId);
}
