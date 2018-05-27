package coder.learning.api.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import java.io.IOException;

/**
 * Created by xin.liu on 2018/5/1.
 */
public class JsonTestUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String RESOURCE_PATH = "t1/";

    public static<T> T readObject(String path,Class<T> clazz) {
        try {
            System.out.println("==========");
            System.out.println(objectMapper.readValue(new ClassPathResource(RESOURCE_PATH + path).getInputStream(), clazz));
            System.out.println("==========");
            return objectMapper.readValue(new ClassPathResource(RESOURCE_PATH + path).getInputStream(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
