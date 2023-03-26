package com.hunk.music.dao;

import com.hunk.music.domain.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单的歌曲管理dao
 */
@Repository
public interface ListSongMapper {
    /**
     * 增加
     */
    public int insert(ListSong listSong);

    /**
     * 修改
     */
    public int update(ListSong listSong);

    /**
     * 删除
     */
    public int delete(Integer id);

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
