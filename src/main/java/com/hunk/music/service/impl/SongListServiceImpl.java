package com.hunk.music.service.impl;

import com.hunk.music.dao.SongListMapper;
import com.hunk.music.domain.SongList;
import com.hunk.music.service.SongListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 歌单service接口实现类
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Resource
    private SongListMapper songListMapper;

    /**
     * 增加
     *
     * @param songList
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList) > 0;
    }

    /**
     * 修改
     *
     * @param songList
     */
    @Override
    public boolean update(SongList songList) {
        return songListMapper.update(songList) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return songListMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询
     *
     * @param id
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     */
    @Override
    public List<SongList> selectAll() {
        return songListMapper.selectAll();
    }

    /**
     * 根据风格查询
     *
     * @param style
     */
    @Override
    public List<SongList> selectByStyle(String style) {
        return songListMapper.selectByStyle(style);
    }
}
