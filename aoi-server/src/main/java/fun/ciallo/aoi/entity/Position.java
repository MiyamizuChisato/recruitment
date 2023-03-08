package fun.ciallo.aoi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@TableName
public class Position {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String wage;
    private Integer hot;
    private Integer companyId;
    private Integer userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer status;
}
