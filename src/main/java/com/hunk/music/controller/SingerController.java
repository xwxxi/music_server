package com.hunk.music.controller;

import com.hunk.music.domain.Singer;
import com.hunk.music.service.SingerService;
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
 * 歌手接口层
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Resource
    private SingerService singerService;

    /**
     * 添加歌手
     * @param singer
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R addSinger(@RequestBody Singer singer){
        boolean ret = singerService.insert(singer);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("message","添加成功");
            return R.ok(map);
        }else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改歌手
     * @param singer
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R updateSinger(@RequestBody Singer singer){
        boolean ret = singerService.update(singer);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("message","修改成功");
            return R.ok(map);
        }else {
            return R.error("修改失败");
        }
    }

    /**
     * 删除歌手
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public R deleteSinger( Integer id){
        boolean ret = singerService.delete(id);
        Map<String, Object> map = new HashMap<>();
        map.put("message",ret);
        return R.ok(map);
    }

    /**
     * 根据id查询歌手
     * @return
     */
    @RequestMapping(value = "/getSingerById", method = RequestMethod.GET)
    public R getSingerById( Integer id){
        Singer singer = singerService.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<>();
        map.put("message",singer);
        return R.ok(map);
    }

    /**
     * 根据名字模糊查询
     * @return
     */
    @RequestMapping(value = "/getSingerOfName", method = RequestMethod.GET)
    public R getSingerOfName( String name){
        System.out.println(name);
        List<Singer> singerList = singerService.singerOfName("%"+name+"%");
        Map<String, Object> map = new HashMap<>();
        map.put("message",singerList);
        return R.ok(map);
    }

    /**
     * 根据性别查询歌手
     * @return
     */
    @RequestMapping(value = "/getSingerBySex", method = RequestMethod.GET)
    public R getSingerBySex( Integer id){
        List<Singer> singerList = singerService.singerOfSex(id);
        Map<String, Object> map = new HashMap<>();
        map.put("message",singerList);
        return R.ok(map);
    }

    /**
     * 查询所有歌手
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public R selectAll(){
        List<Singer> singers = singerService.selectAll();
        Map<String, Object> map = new HashMap<>();
        map.put("message",singers);
        return R.ok(map);
    }
}
