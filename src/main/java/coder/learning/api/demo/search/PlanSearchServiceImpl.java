package coder.learning.api.demo.search;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/7/4.
 */
@Service
public class PlanSearchServiceImpl implements PlanSearchService{
    @Override
    public PlanSearchResponse planSearch(PlanSearchRequest request) {

        List<ScheduleInfo> list = request.getScheduleInfos();

        Integer count = list.size();
        LinkedList<ScheduleInfo> scheduleInfos = new LinkedList<>();
        scheduleInfos.add(list.get(0));
        list.remove(0);

        List<PlanInfo> planInfos = list.stream().map(nextSchedule -> {
            ScheduleInfo lastSchedule = scheduleInfos.getLast();
            scheduleInfos.add(nextSchedule);
            return CommonUtil.planInfoCreater(lastSchedule, nextSchedule, count);
        }).collect(Collectors.toList());

        PlanSearchResponse response = new PlanSearchResponse();
        response.setPlanInfos(planInfos);
        return response;
    }
}
