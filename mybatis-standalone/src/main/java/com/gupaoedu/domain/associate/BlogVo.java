package com.gupaoedu.domain.associate;

import java.io.Serializable;
import java.util.Arrays;

public class BlogVo implements Serializable {
    Integer bid; // 文章ID
    Integer[] name; // 文章标题
    Integer authorId; // 文章作者ID

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer[] getName() {
        return name;
    }

    public void setName(Integer[] name) {
        this.name = name;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BlogVo{" +
                "bid=" + bid +
                ", name=" + Arrays.toString(name) +
                ", authorId=" + authorId +
                '}';
    }
}
