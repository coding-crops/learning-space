package coder.learning.api.demo.search;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by Administrator on 2018/7/4.
 */
@Data
@Accessors(chain = true)
public class PlanInfo {
    private List<FlightInfo> flightInfos;
}
