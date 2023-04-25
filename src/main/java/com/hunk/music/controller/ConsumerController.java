package com.hunk.music.controller;

import com.hunk.music.domain.Consumer;
import com.hunk.music.service.ConsumerService;
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
 * 用户管理接口接口层
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {


    /**
     * 默认的歌曲头像地址
     */
    private String picPath = "/img/userAvatorImages/hhh.jpg";

    @Resource
    private ConsumerService consumerService;

    /**
     * 添加用户
     *
     * @param consumer
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R addConsumer(@RequestBody Consumer consumer) {
        System.out.println(consumer.toString());
        boolean ret = consumerService.insert(consumer);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "添加成功");
            return R.ok(map);
        } else {
            return R.error("添加失败");
        }
    }


    /**
     * 根据用户id进行查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public R consumerOfId(Integer id) {
        System.out.println(id);
        Consumer consumer = consumerService.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", consumer);
        return R.ok(map);
    }

    /**
     * 根据用户名称进行模糊查询
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/consumerOfUserName", method = RequestMethod.GET)
    public R consumerOfUserName(String userName) {
        System.out.println(userName);
        List<Consumer> consumers = consumerService.consumerOfUserName('%'+userName+'%');
        Map<String, Object> map = new HashMap<>();
        map.put("msg", consumers);
        return R.ok(map);
    }


    /**
     * 查询所有用户
     *
     * @return
     */
    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public R selectAll() {
        List<Consumer> consumers = consumerService.selectAll();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", consumers);
        return R.ok(map);
    }

    /**
     * 删除用户
     *
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public R deleteConsumer(Integer id) {
        boolean ret = consumerService.delete(id);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "删除成功");
            return R.ok(map);
        } else {
            return R.error("删除失败");
        }
    }


    /**
     * 修改用户
     *
     * @param consumer
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public R updateConsumer(@RequestBody Consumer consumer) {
        boolean ret = consumerService.update(consumer);
        Map<String, Object> map = new HashMap<>();
        if (ret) {
            map.put("msg", "修改成功");
            return R.ok(map);
        } else {
            return R.error("修改失败");
        }
    }

    /**
     * 根据性别查询用户
     *
     * @return
     */
    @RequestMapping(value = "/getConsumerBySex", method = RequestMethod.GET)
    public R getConsumerBySex(Integer id) {
        List<Consumer> consumers = consumerService.consumerOfSex(id);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", consumers);
        return R.ok(map);
    }

    /**
     * 更歌手头像文件
     *
     * @param file
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateConsumerPic", method = RequestMethod.POST)
    public R updateConsumerPic(MultipartFile file, Integer id) {
        if (file.isEmpty()) {
            return R.error("文件上传失败");
        }
        Consumer consumerObj = consumerService.selectByPrimaryKey(id);
        // 如果歌手头像不是默认的就把图片资源删除
        if (!consumerObj.getAvator().equals(picPath)) {
            File tempFile = new File(consumerObj.getAvator().trim());
            String fileName = tempFile.getName();
            String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                    + System.getProperty("file.separator") + "userAvatorImages" + System.getProperty("file.separator") + fileName;
            System.out.println(filePath);
            FileSystemUtils.deleteRecursively(new File(filePath));
        }

        // 获取文件明  加上毫秒值是为了区分相同的名称
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();
        // 服务器存储图片的路径
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "userAvatorImages";

        // 判断路径是否存在 如果不存在就新增
        File temFile = new File(filePath);
        if (!temFile.exists()) {
            temFile.mkdir();
        }

        // 实际静态资源存放的地方
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);

        // 数据库里写的地址
        String storeAvatarPath = "/img/userAvatorImages/" + fileName;

        try {
            file.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatarPath);
            boolean update = consumerService.update(consumer);
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
