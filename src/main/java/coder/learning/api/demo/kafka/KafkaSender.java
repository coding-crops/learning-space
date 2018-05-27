package coder.learning.api.demo.kafka;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息方法
    public void send(String m) throws JsonProcessingException {
        Message message = new Message();
        message.setId(System.currentTimeMillis());
        // message.setMsg(UUID.randomUUID().toString());
        message.setMsg(m);
        message.setSendTime(new Date());
        ObjectMapper mapper = new ObjectMapper();
        String msg = mapper.writeValueAsString(message);
        log.info("+++++++++++++++++++++  message = {}", msg);
        kafkaTemplate.send("zhisheng", msg);
    }
}