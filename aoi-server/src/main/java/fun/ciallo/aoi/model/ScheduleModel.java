package fun.ciallo.aoi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleModel {
    private int id;
    private int progress;
    private UserSimpleModel user;
    private PositionModel position;
}
