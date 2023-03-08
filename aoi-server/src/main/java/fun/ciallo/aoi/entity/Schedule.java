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
public class Schedule {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer progress;
    private Integer positionId;
    private Integer publisher;
    private Integer asker;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
