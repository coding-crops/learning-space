package coder.learning.api.demo.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/4.
 */
@RestController
@RequiredArgsConstructor
public class SearchController {
    private final PlanSearchService service;
    @PostMapping(value = "/search", produces = MediaType.APPLICATION_XML_VALUE)
    public PlanSearchResponse search(@RequestBody PlanSearchRequest request) throws JsonProcessingException {
//        XmlMapper mapper = new XmlMapper();
//        List<ScheduleInfo> x = new ArrayList<>();
//        x.add(new ScheduleInfo());
//        System.out.println(mapper.writeValueAsString(new PlanSearchRequest().setScheduleInfos(x)));
//        System.out.print
        return service.planSearch(request);
//        return null;
    }
}
