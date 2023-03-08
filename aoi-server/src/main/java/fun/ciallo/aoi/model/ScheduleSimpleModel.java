package fun.ciallo.aoi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleSimpleModel {
    private int id;
    private int progress;
    private UserProfileModel user;
}
