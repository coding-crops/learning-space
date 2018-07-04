package coder.learning.api.demo.search;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by Administrator on 2018/7/4.
 */
@Data
@Accessors(chain = true)
public class PlanSearchRequest {
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ScheduleInfo> scheduleInfos;
    private String directFlag;
}
