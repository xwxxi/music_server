package com.hunk.music.controller;

import com.hunk.music.domain.Song;
import com.hunk.music.service.SongService;
import com.hunk.music.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 歌曲管理接口接口层
 */
@RestController
@RequestMapping("/song")
public class SongController {


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
            boolean update = songService.insert(song);
            Map<String, Object> map = new HashMap<>();
            if (update) {
                map.put("msg", "更新成功");
                map.put("avatar", storeAvatarPath);
                return R.ok(map);
            } else {
                return R.error("更新失败");
            }
        } catch (IOException e) {
            return R.error("发生异常" + e.getMessage());
        }
    }

    public R updateSong(){
        return R.ok();
    }
}
