package org.lk.daydayup.pojo;

public class ItemUtil {
    String topicId;    // 所属topic
    String title;
    String url; // 资料的url, 一般是B站, 知乎, csdn链接
    String intro;   // 贡献者对该资料的说明

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getIntro() {
        return intro;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
