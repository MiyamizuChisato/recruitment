package fun.ciallo.aoi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.aoi.entity.Schedule;
import fun.ciallo.aoi.model.ScheduleModel;
import fun.ciallo.aoi.model.ScheduleSimpleModel;

public interface ScheduleService extends IService<Schedule> {
    Page<ScheduleSimpleModel> pageSchedule(Page<Schedule> page, int positionId, int uid);

    Page<ScheduleModel> mineSchedule(Page<Schedule> page, int uid);
}
