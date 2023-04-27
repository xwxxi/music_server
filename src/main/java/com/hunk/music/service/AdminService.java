package com.hunk.music.service;

/**
 * 管理员service接口
 */
public interface AdminService {

    /**
     * 验证密码是否正确
     */
    public boolean verifyPassword(String name, String password);

    public int consumerCount();

    public int songCount();

    public int singerCount();

    public int songListCount();

    public int getConsumerSexCountByWoman();

    public int getConsumerSexCountByMale();
}
