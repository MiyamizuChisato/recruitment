package fun.ciallo.aoi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.aoi.entity.Position;
import fun.ciallo.aoi.model.PositionCardModel;
import fun.ciallo.aoi.model.PositionManageModel;
import fun.ciallo.aoi.model.PositionModel;

import java.util.List;

public interface PositionService extends IService<Position> {
    List<PositionManageModel> getManagePosition(int uid);

    Page<PositionCardModel> pagePositionCard(Page<Position> page);

    PositionCardModel build(PositionCardModel model, Position position);

    PositionModel build(PositionModel model, Position position);

    Page<PositionCardModel> search(Page<Position> page, String keyword);

    List<PositionCardModel> carousel();
}
