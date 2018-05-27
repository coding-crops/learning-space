package coder.learning.api.demo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xin.liu on 2018/5/1.
 */
@RestController
public class Demo {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/demo")
    public String demo() {
        
        return (String)redisTemplate.opsForValue().get("name");
    }
}
