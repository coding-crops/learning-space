package coder.learning.api.demo.optional;

import lombok.Data;
import java.util.Optional;

/**
 * Created by Administrator on 2018/5/1.
 */
@Data
public class Car {
    private Optional<Insurance> insurance;
}
