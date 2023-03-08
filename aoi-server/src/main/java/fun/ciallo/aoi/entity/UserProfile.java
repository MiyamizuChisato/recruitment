package fun.ciallo.aoi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@TableName
public class UserProfile {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String nickname;
    private String avatar;
    private String motto;
    private String email;
    private Integer identity;
    private Integer type;
    private String resume;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
