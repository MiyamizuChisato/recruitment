package fun.ciallo.aoi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName
public class Company {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String logo;
    private String location;
    private String appendix;
    private Integer status;
    private Integer userId;
}
