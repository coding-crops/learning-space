package coder.learning.api.demo.search;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Administrator on 2018/7/4.
 */
@Data
@Accessors(chain = true)
public class FlightInfo {
    private String arrival;
    private String departure;
    private String arrivalDate;
    private String arrivalTime;
    private String departureDate;
    private String departureTime;
}
