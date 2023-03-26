package com.hunk.music.controller;

import com.hunk.music.domain.SongList;
import com.hunk.music.service.SongListService;
import com.hunk.music.utils.R;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 歌单管理接口接口层
 */
@RestController
@RequestMapping("/songList")
public class SongListController {


    /**
     * 默认的歌单头像地址
     */
    private String picPath = "/img/songListPic/tubiao.jpg";

    @Resource
    private SongListService songListService;

    /**
     * 添加歌单
     *
     * @param songList
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R addSSongList(@RequestBody SongList songList) {
        boolean ret = songListService.insert(songList);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "添加成功");
            return R.ok(map);
        } else {
            return R.error("添加失败");
        }
    }

    /**
     * 修改歌单
     *
     * @param songList
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R updateSongList(@RequestBody SongList songList) {
        boolean ret = songListService.update(songList);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "修改成功");
            return R.ok(map);
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 删除歌单
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public R deleteSongList(Integer id) {
        SongList songList = songListService.selectByPrimaryKey(id);
        // 如果歌单图标不是默认的就把图片资源删除
        if (!songList.getPic().equals(picPath)) {
            File tempFile = new File(songList.getPic().trim());
            String fileName = tempFile.getName();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                    + System.getProperty("file.separator") + "songListPic" + System.getProperty("file.separator") + fileName;
            System.out.println(filePath);
            FileSystemUtils.deleteRecursively(new File(filePath));
        }
        boolean ret = songListService.delete(id);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "删除成功");
            return R.ok(map);
        } else {
            return R.error("删除失败");
        }
    }

    /**
     * 根据id查询歌单
     *
     * @return
     */
    @RequestMapping(value = "/getSongListById", method = RequestMethod.GET)
    public R getSongListById(Integer id) {
        SongList songList = songListService.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", songList);
        return R.ok(map);
    }

    /**
     * 根据风格查询歌单
     *
     * @return
     */
    @RequestMapping(value = "/getSongListByStyle", method = RequestMethod.GET)
    public R getSongListByStyle(String style) {
        List<SongList> songLists = songListService.selectByStyle(style);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", songLists);
        return R.ok(map);
    }


    /**
     * 查询所有歌单
     *
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public R selectAll() {
        List<SongList> songLists = songListService.selectAll();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", songLists);
        return R.ok(map);
    }

    /**
     * 更歌单图标文件
     *
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongListPic", method = RequestMethod.POST)
    public R updateSongListPic(MultipartFile file, Integer id) {
        if (file.isEmpty()) {
            return R.error("文件上传失败");
        }

        SongList songListObj = songListService.selectByPrimaryKey(id);
        // 如果歌手头像不是默认的就把图片资源删除
        if (!songListObj.getPic().equals(picPath)) {
            File tempFile = new File(songListObj.getPic().trim());
            String fileName = tempFile.getName();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                    + System.getProperty("file.separator") + "songListPic" + System.getProperty("file.separator") + fileName;
            System.out.println(filePath);
            FileSystemUtils.deleteRecursively(new File(filePath));
        }

        // 获取文件明  加上毫秒值是为了区分相同的名称
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        // 服务器存储图片的路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songListPic";

        // 判断路径是否存在 如果不存在就新增
        File temFile = new File(filePath);
        if (!temFile.exists()) {
            temFile.mkdir();
        }

        // 实际静态资源存放的地方
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 数据库里写的地址
        String storeAvatarPath = "/img/songListPic/" + fileName;

        try {
            file.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatarPath);
            boolean update = songListService.update(songList);
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
}
