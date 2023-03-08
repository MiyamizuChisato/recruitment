package fun.ciallo.aoi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName
public class Relation {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sender;
    private Integer receiver;
    private Integer unread;

}
