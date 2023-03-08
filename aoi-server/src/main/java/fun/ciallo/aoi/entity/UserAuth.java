package fun.ciallo.aoi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName
public class UserAuth {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private Integer profileId;
}
