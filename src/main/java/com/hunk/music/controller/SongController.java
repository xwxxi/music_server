package com.hunk.music.controller;

import com.hunk.music.domain.Song;
import com.hunk.music.service.SongService;
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
 * 歌曲管理接口接口层
 */
@RestController
@RequestMapping("/song")
public class SongController {


    /**
     * 默认的歌曲头像地址
     */
    private String picPath = "/img/songPic/tubiao.jpg";

    @Resource
    private SongService songService;

    /**
     * 添加歌曲
     *
     * @param file
     * @param song
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R addSong(MultipartFile file, Song song) {
        if (file.isEmpty()) {
            return R.error("文件上传失败");
        }
        // 获取文件明  加上毫秒值是为了区分相同的名称
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        // 服务器存储图片的路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";

        // 判断路径是否存在 如果不存在就新增
        File temFile = new File(filePath);
        if (!temFile.exists()) {
            temFile.mkdir();
        }

        // 实际静态资源存放的地方
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 数据库里写的地址
        String storeAvatarPath = "/song/" + fileName;

        try {
            file.transferTo(dest);
            song.setUrl(storeAvatarPath);
            boolean update = songService.insert(song);
            Map<String, Object> map = new HashMap<>();
            if (update) {
                map.put("msg", "添加成功");
                map.put("avatar", storeAvatarPath);
                return R.ok(map);
            } else {
                return R.error("添加失败");
            }
        } catch (IOException e) {
            return R.error("发生异常" + e.getMessage());
        }
    }


    /**
     * 根据歌手id进行查询
     *
     * @param singerId
     * @return
     */
    @RequestMapping(value = "/singer/detail", method = RequestMethod.GET)
    public R songOfSingerId(Integer singerId) {
        System.out.println(singerId);
        List<Song> songs = songService.songOfSingerId(singerId);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", songs);
        return R.ok(map);
    }

    /**
     * 根据歌曲名称进行模糊查询
     *
     * @param songName
     * @return
     */
    @RequestMapping(value = "/songOfName", method = RequestMethod.GET)
    public R songOfName(String songName) {
        System.out.println(songName);
        List<Song> songs = songService.songOfName('%'+songName+'%');
        Map<String, Object> map = new HashMap<>();
        map.put("msg", songs);
        return R.ok(map);
    }

    /**
     * 删除歌曲
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public R deleteSong(Integer id) {
        Song song = songService.selectByPrimaryKey(id);
        // 如果歌曲头像不是默认的就把图片资源删除
        if (song.getPic() != null && !song.getPic().equals(picPath)) {
            File tempFile = new File(song.getPic().trim());
            String fileName = tempFile.getName();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                    + System.getProperty("file.separator") + "songPic" + System.getProperty("file.separator") + fileName;
            System.out.println(filePath);
            FileSystemUtils.deleteRecursively(new File(filePath));
        }
        // 如果有上传的歌曲文件，删除时同样删除
        if (song.getUrl() != null) {
            File tempFile = new File(song.getUrl().trim());
            String fileName = tempFile.getName();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song"
                    + System.getProperty("file.separator") + fileName;
            System.out.println(filePath);
            FileSystemUtils.deleteRecursively(new File(filePath));
        }
        // 删除数据库记录
        boolean ret = songService.delete(id);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "删除成功");
            return R.ok(map);
        } else {
            return R.error("删除失败");
        }
    }


    /**
     * 修改歌曲
     *
     * @param song
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R updateSong(@RequestBody Song song) {
        boolean ret = songService.update(song);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "修改成功");
            return R.ok(map);
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 更歌手歌曲图标文件
     *
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongPic", method = RequestMethod.POST)
    public R updateSongPic(MultipartFile file, Integer id) {
        if (file.isEmpty()) {
            return R.error("文件上传失败");
        }

        Song songObj = songService.selectByPrimaryKey(id);
        if (songObj.getPic() != null && !songObj.getPic().equals(picPath)) {
            File tempFile = new File(songObj.getPic().trim());
            String fileName = tempFile.getName();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                    + System.getProperty("file.separator") + "songPic" + System.getProperty("file.separator") + fileName;
            System.out.println(filePath);
            FileSystemUtils.deleteRecursively(new File(filePath));
        }

        // 获取文件明  加上毫秒值是为了区分相同的名称
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        // 服务器存储图片的路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "songPic";

        // 判断路径是否存在 如果不存在就新增
        File temFile = new File(filePath);
        if (!temFile.exists()) {
            temFile.mkdir();
        }

        // 实际静态资源存放的地方
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 数据库里写的地址
        String storeAvatarPath = "/img/songPic/" + fileName;

        try {
            file.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatarPath);
            boolean update = songService.update(song);
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
     * 更歌手歌曲文件
     *
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateSongFile", method = RequestMethod.POST)
    public R updateSongFile(MultipartFile file, Integer id) {
        if (file.isEmpty()) {
            return R.error("文件上传失败");
        }

        Song songObj = songService.selectByPrimaryKey(id);
        if (songObj.getUrl() != null) {
            File tempFile = new File(songObj.getUrl().trim());
            String fileName = tempFile.getName();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song"
                    + System.getProperty("file.separator") + fileName;
            System.out.println(filePath);
            FileSystemUtils.deleteRecursively(new File(filePath));
        }

        // 获取文件明  加上毫秒值是为了区分相同的名称
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        // 服务器存储图片的路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";

        // 判断路径是否存在 如果不存在就新增
        File temFile = new File(filePath);
        if (!temFile.exists()) {
            temFile.mkdir();
        }

        // 实际静态资源存放的地方
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 数据库里写的地址
        String storeAvatarPath = "/song/" + fileName;

        try {
            file.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatarPath);
            boolean update = songService.update(song);
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
