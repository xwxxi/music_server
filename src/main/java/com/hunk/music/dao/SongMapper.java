package com.hunk.music.dao;

import com.hunk.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲管理dao
 */
@Repository
public interface SongMapper {
    /**
     * 增加
     */
    public int insert(Song song);

    /**
     * 修改
     */
    public int update(Song song);

    /**
     * 删除
     */
    public int delete(Integer id);

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
    public List<Song> songOfSingerId(Integer singerId);

}
