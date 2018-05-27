package coder.learning.api.demo.mapstruct;

import coder.learning.api.test.JsonTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by xin.liu on 2018/5/1.
 */
public class FlightMapperTest {

    private static final String RESOURCEPATH = "flight/";

    @Test
    public void mappingFromAirToWts() {
        FlightForAir input = JsonTestUtils.readObject(RESOURCEPATH + "FlightForAir.json", FlightForAir.class);
        FlightForWts expected = JsonTestUtils.readObject(RESOURCEPATH + "FlightForWts.json", FlightForWts.class);
        FlightForWts actual = FlightMapper.INSTANCE.mappingFromAirToWts(input);
        Assertions.assertEquals(expected,actual);
    }
}
