package com.hunk.music.domain;

import java.io.Serializable;

/**
 * 歌单-歌曲管理表实体类
 */
public class ListSong implements Serializable {

    /** 自增id */
    private Integer id;

    /** 歌曲id */
    private Integer songId;

    /** 歌单id */
    private Integer songListId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    @Override
    public String toString() {
        return "ListSong{" +
                "id=" + id +
                ", songId=" + songId +
                ", songListId=" + songListId +
                '}';
    }
}
