package com.hunk.music.service.impl;

import com.hunk.music.dao.ListSongMapper;
import com.hunk.music.domain.ListSong;
import com.hunk.music.service.ListSongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 歌单的歌曲service接口实现类
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Resource
    private ListSongMapper listSongMapper;

    /**
     * 增加
     *
     * @param listSong
     */
    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong) > 0;
    }

    /**
     * 修改
     *
     * @param listSong
     */
    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong) > 0;
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public boolean delete(Integer id) {
        return listSongMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询
     *
     * @param id
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     */
    @Override
    public List<ListSong> selectAll() {
        return listSongMapper.selectAll();
    }

    /**
     * 根据歌单id查询所有的歌曲
     *
     * @param songListId
     */
    @Override
    public List<ListSong> getSongBySongListId(Integer songListId) {
        return listSongMapper.getSongBySongListId(songListId);
    }
}
