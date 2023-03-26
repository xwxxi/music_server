package com.hunk.music.domain;

import java.io.Serializable;

/**
 * 歌单管理表实体类
 */
public class SongList implements Serializable {

    /** 自增id */
    private Integer id;

    /** 标题 */
    private String title;

    /** 歌单图片 */
    private String pic;

    /** 简介 */
    private String introduction;

    /** 风格 */
    private String style;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    @Override
    public String toString() {
        return "SongList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", pic='" + pic + '\'' +
                ", introduction='" + introduction + '\'' +
                ", style='" + style + '\'' +
                '}';
    }
}
