package mapper;

import org.apache.ibatis.annotations.Param;

public interface ChecklistMapper {
    String getToDo(int userId);

    // res 为更新后要插入数据库的字符串
    void addToDo(@Param("userId") int userId, @Param("res") String res);
}
