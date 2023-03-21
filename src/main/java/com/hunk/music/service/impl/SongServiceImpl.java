package com.hunk.music.service.impl;

import com.hunk.music.dao.SongMapper;
import com.hunk.music.domain.Song;
import com.hunk.music.service.SongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 歌曲service接口实现类
 */
@Service
public class SongServiceImpl implements SongService {

    @Resource
    private SongMapper songMapper;


    /**
     * 增加
     *
     * @param song
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song) > 0;
    }

    /**
     * 修改
     *
     * @param song
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询
     *
     * @param id
     */
    @Override
    public Song selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     */
    @Override
    public List<Song> selectAll() {
        return songMapper.selectAll();
    }

    /**
     * 根据歌曲名字进行模糊查询
     *
     * @param name
     */
    @Override
    public List<Song> songOfName(String name) {
        return songMapper.songOfName(name);
    }

    /**
     * 根据歌手id查询
     *
     * @param singerId
     */
    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songMapper.songOfSingerId(singerId);
    }
}
