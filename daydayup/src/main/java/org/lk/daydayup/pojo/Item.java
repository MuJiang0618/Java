package org.lk.daydayup.pojo;

// 一个topic中的一行学习资料
public class Item {
    int topicId;    // 所属topic

    String url; // 资料的url, 一般是B站, 知乎, csdn链接
    String intro;   // 贡献者对该资料的说明

    int like;
    int dislike;
}
