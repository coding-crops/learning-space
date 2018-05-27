package coder.learning.api.demo.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private KafkaSender sender;
    
    // @Scheduled(fixedRate =1000)
    @RequestMapping("/kafka/{msg}")
    public void sendMsg(@PathVariable("msg") String msg) throws JsonProcessingException {
        sender.send(msg);
    }
}
