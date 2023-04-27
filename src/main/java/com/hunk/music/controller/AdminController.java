package com.hunk.music.controller;

import com.hunk.music.domain.Admin;
import com.hunk.music.service.AdminService;
import com.hunk.music.utils.Consts;
import com.hunk.music.utils.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 管理员接口层
 */
@RestController
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 判断是否登录成功
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public R loginStatus(@RequestBody Admin admin, HttpSession session) {
        String name = admin.getName();
        String password = admin.getPassword();
        System.out.println(name+"--"+password);
        boolean flag = adminService.verifyPassword(name, password);
        if (flag) {
            session.setAttribute(Consts.NAME, name);
            return R.ok("登录成功");
        }
        return R.error("登录失败");
    }

    @RequestMapping(value = "/admin/dataCount", method = RequestMethod.GET)
    public R dataCount(){
        int consumerCount = adminService.consumerCount();
        int singerCount = adminService.singerCount();
        int songCount = adminService.songCount();
        int songListCount = adminService.songListCount();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "成功");
        map.put("consumerCount", consumerCount);
        map.put("singerCount", singerCount);
        map.put("songCount", songCount);
        map.put("songListCount", songListCount);
        return R.ok(map);
    }


    @RequestMapping(value = "/admin/getConsumerSexCount", method = RequestMethod.GET)
    public R getConsumerSexCount(){
        int maleCount = adminService.getConsumerSexCountByMale();
        int womanCount = adminService.getConsumerSexCountByWoman();
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "成功");
        map.put("maleCount", maleCount);
        map.put("womanCount", womanCount);
        return R.ok(map);
    }
}
