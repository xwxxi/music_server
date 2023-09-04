package com.hunk.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hunk.music.dao")
public class MusicApplication {

    // 启动
    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class, args);
    }

    // 测试
}
