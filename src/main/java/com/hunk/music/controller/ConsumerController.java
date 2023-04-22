package com.hunk.music.controller;

import com.hunk.music.domain.Consumer;
import com.hunk.music.service.ConsumerService;
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
 * 用户管理接口接口层
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {


    /**
     * 默认的歌曲头像地址
     */
    private String picPath = "/img/songPic/tubiao.jpg";

    @Resource
    private ConsumerService consumerService;

    /**
     * 添加用户
     *
     * @param consumer
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public R addConsumer(Consumer consumer) {
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

}
