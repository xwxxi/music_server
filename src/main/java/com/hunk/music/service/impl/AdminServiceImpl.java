package com.hunk.music.service.impl;

import com.hunk.music.dao.AdminMapper;
import com.hunk.music.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员service接口实现类
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     * @param name
     * @param password
     */
    @Override
    public boolean verifyPassword(String name, String password) {
        int userNum = adminMapper.verifyPassword(name, password);
        if (userNum > 0){
            return true;
        }
        return false;
    }

    @Override
    public int consumerCount() {
        return adminMapper.consumerCount();
    }

    @Override
    public int songCount() {
        return adminMapper.songCount();
    }

    @Override
    public int singerCount() {
        return adminMapper.singerCount();
    }

    @Override
    public int songListCount() {
        return adminMapper.songListCount();
    }

    @Override
    public int getConsumerSexCountByWoman() {
        return adminMapper.getConsumerSexCountByWoman();
    }

    @Override
    public int getConsumerSexCountByMale() {
        return adminMapper.getConsumerSexCountByMale();
    }
}
