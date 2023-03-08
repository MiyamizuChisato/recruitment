package fun.ciallo.aoi.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PositionModel {
    private int id;
    private String title;
    private String content;
    private String wage;
    private int hot;
    private CompanyModel company;
    private UserSimpleModel user;
    private LocalDateTime updateTime;
}
