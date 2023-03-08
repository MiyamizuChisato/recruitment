package fun.ciallo.aoi.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionCardModel {
    private int id;
    private String title;
    private String wage;
    private int hot;
    private CompanyModel company;
    private UserSimpleModel user;
}
