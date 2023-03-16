package com.hunk.music.controller;

import com.github.pagehelper.PageInfo;
import com.hunk.music.domain.Singer;
import com.hunk.music.service.SingerService;
import com.hunk.music.utils.R;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
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
     *
     * @param singer
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R addSinger(@RequestBody Singer singer) {
        System.out.println(singer.toString());
        boolean ret = singerService.insert(singer);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "添加成功");
            return R.ok(map);
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改歌手
     *
     * @param singer
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R updateSinger(@RequestBody Singer singer) {
        boolean ret = singerService.update(singer);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "修改成功");
            return R.ok(map);
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 删除歌手
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public R deleteSinger(Integer id) {
        boolean ret = singerService.delete(id);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "删除成功");
            return R.ok(map);
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 根据id查询歌手
     *
     * @return
     */
    @RequestMapping(value = "/getSingerById", method = RequestMethod.GET)
    public R getSingerById(Integer id) {
        Singer singer = singerService.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", singer);
        return R.ok(map);
    }

    /**
     * 根据名字模糊查询
     *
     * @return
     */
    @RequestMapping(value = "/getSingerOfName", method = RequestMethod.GET)
    public R getSingerOfName(String name) {
        System.out.println(name);
        List<Singer> singerList = singerService.singerOfName("%" + name + "%");
        Map<String, Object> map = new HashMap<>();
        map.put("msg", singerList);
        return R.ok(map);
    }

    /**
     * 根据性别查询歌手
     *
     * @return
     */
    @RequestMapping(value = "/getSingerBySex", method = RequestMethod.GET)
    public R getSingerBySex(Integer id) {
        List<Singer> singerList = singerService.singerOfSex(id);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", singerList);
        return R.ok(map);
    }

    /**
     * 查询所有歌手
     *
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public R selectAll() {
        List<Singer> singers = singerService.selectAll();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", singers);
        return R.ok(map);
    }

    /**
     * 更歌手头像文件
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSingerPic", method = RequestMethod.POST)
    public R updateSingerPic(MultipartFile file, Integer id) {
        if (file.isEmpty()) {
            return R.error("文件上传失败");
        }
        // 获取文件明  加上毫秒值是为了区分相同的名称
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        // 服务器存储图片的路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";

        // 判断路径是否存在 如果不存在就新增
        File temFile = new File(filePath);
        if (!temFile.exists()) {
            temFile.mkdir();
        }

        // 实际静态资源存放的地方
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatarPath = "/img/singerPic/" + fileName;

        try {
            file.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatarPath);
            boolean update = singerService.update(singer);
            Map<String, Object> map = new HashMap<>();
            if (update) {
                map.put("msg", "更新成功");
                map.put("pic", storeAvatarPath);
                return R.ok(map);
            } else {
                return R.error("更新失败");
            }
        } catch (IOException e) {
            return R.error("发生异常" + e.getMessage());
        }
    }

    /**
     * 分页
     */
    @CrossOrigin
    @RequestMapping(value = "/pagehelper/{pageCode}/{pageSize}",method = RequestMethod.GET)
    public PageInfo<Singer> findByPage(@PathVariable(value = "pageCode") int pageCode, @PathVariable(value = "pageSize") int pageSize) {
        System.out.println(pageCode+"...."+pageSize);
        PageInfo<Singer> pageInfo = singerService.findByPageService(pageCode, pageSize);
        return pageInfo;
    }
}
