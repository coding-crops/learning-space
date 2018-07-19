package coder.learning.api.demo.document;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2018/7/19.
 */
@RestController
public class DocumentController {

    @RequestMapping("/document")
    public String getDocument(HttpServletRequest request) {
        try (InputStream inputStream = request.getInputStream()) {
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            System.out.println(document.asXML());
            System.out.println("======"+document.selectNodes("*/user").isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return "";
    }
}
