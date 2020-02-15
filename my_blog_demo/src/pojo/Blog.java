package pojo;

import org.apache.ibatis.jdbc.Null;
import org.apache.solr.client.solrj.beans.Field;

import java.time.DateTimeException;
import java.util.Date;

public class Blog {

    @Field("blog_id") public int blog_id;
    public int author_id;
    public String author_name;
    @Field("title") public String title;
    @Field("content") public String content;
    public Date create_time;
    @Field("like") public int like;

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getLike() {
        return like;
    }
}
