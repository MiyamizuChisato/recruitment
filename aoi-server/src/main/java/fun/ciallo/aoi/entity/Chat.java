package fun.ciallo.aoi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@TableName
public class Chat {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer sender;
    private Integer receiver;
    private String content;
}
