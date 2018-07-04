package coder.learning.api.demo.search;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2018/7/4.
 */
@Data
@Accessors(chain = true)
public class ScheduleInfo {
    private String arrivalCity;
    private String departureCity;
    private String departureDate;
    private String departureTime;
}
