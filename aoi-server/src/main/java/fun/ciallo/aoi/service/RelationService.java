package fun.ciallo.aoi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import fun.ciallo.aoi.entity.Relation;
import fun.ciallo.aoi.model.RelationModel;

public interface RelationService extends IService<Relation> {
    Page<RelationModel> getReceivers(Page<Relation> page, int uid);
}
