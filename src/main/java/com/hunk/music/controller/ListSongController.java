package com.hunk.music.controller;

import com.hunk.music.domain.ListSong;
import com.hunk.music.service.ListSongService;
import com.hunk.music.utils.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 歌单的歌曲管理接口层
 */
@RestController
@RequestMapping("/listSong")
public class ListSongController {


    @Resource
    private ListSongService listSongService;

    /**
     * 歌单添加歌曲
     *
     * @param listSong
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R addSong(@RequestBody ListSong listSong) {
        boolean ret = listSongService.insert(listSong);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "添加成功");
            return R.ok(map);
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 删除歌单中的歌曲
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public R deleteSong(Integer id) {
        boolean ret = listSongService.delete(id);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "删除成功");
            return R.ok(map);
        } else {
            return R.error("删除失败");
        }
    }


    /**
     * 修改歌单中的歌曲
     *
     * @param listSong
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R updateSong(@RequestBody ListSong listSong) {
        boolean ret = listSongService.update(listSong);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "修改成功");
            return R.ok(map);
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 根据主键查询歌单歌曲
     *
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.GET)
    public R selectByPrimaryKey(Integer id) {
        ListSong listSong = listSongService.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", listSong);
        return R.ok(map);
    }

    /**
     * 查询所有歌单歌曲
     *
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public R selectAll() {
        List<ListSong> listSongs = listSongService.selectAll();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", listSongs);
        return R.ok(map);
    }

    /**
     * 根据主键查询歌单歌曲
     *
     * @return
     */
    @RequestMapping(value = "/getSongBySongListId", method = RequestMethod.GET)
    public R getSongBySongListId(Integer songListId) {
        List<ListSong> listSongs = listSongService.getSongBySongListId(songListId);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", listSongs);
        return R.ok(map);
    }

}
