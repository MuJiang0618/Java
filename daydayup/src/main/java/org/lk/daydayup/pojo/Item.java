package org.lk.daydayup.pojo;

// 一个topic中的一行学习资料
public class Item {
    int topicId;    // 所属topic

    String url; // 资料的url, 一般是B站, 知乎, csdn链接
    String intro;   // 贡献者对该资料的说明

    int likes;
    int dislikes;

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
