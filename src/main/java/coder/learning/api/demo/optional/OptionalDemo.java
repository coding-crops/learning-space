package coder.learning.api.demo.optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

/**
 * Created by Administrator on 2018/5/1.
 */
@RestController
@RequestMapping("/optional")
public class OptionalDemo {

    @GetMapping("/demo")
    public String getInsurance() {
        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);
        Optional<Optional<Car>> optCarFromMap = optPerson.map(Person::getCar);
        Optional<Car> optCarFromFlatmap = optPerson.flatMap(Person::getCar);
        
        return "";
    }
}
