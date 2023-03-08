package fun.ciallo.aoi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelationModel {
    private int id;
    private UserSimpleModel receiver;
    private int unread;
}
