package com.how2java.springboot.pojo;

import java.util.Date;

public class Blog {
    int blogId;
    int authorId;
    String authorName;
    String title;
    String content;
    Date createTime;
    int likes = 0;  // 点赞数

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public Date getCreateTime() {
        return createTime;
    }
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }
}
