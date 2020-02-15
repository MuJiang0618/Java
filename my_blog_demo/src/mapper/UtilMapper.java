package mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UtilMapper {
    void updateProfileImg(@Param("userId") int userId, @Param("imgName") String imgName);

}
