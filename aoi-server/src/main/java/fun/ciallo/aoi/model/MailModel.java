package fun.ciallo.aoi.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class MailModel {
    private String taker;
    private String title;
    private Map<String, Object> params;
}
