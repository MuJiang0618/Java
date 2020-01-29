package pojo;

public class UserData {
    public int userId;
    public String[] blogCategory;
    public String imgSrc;   // 不同category之间用空格隔开, 从数据库取出用split解析为String[], checklist同
    public String[] checklist;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setBlogCategory(String[] blogCategory) {
        this.blogCategory = blogCategory;
    }

    public String[] getBlogCategory() {
        return blogCategory;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setChecklist(String[] checklist) {
        this.checklist = checklist;
    }

    public String[] getChecklist() {
        return checklist;
    }
}
