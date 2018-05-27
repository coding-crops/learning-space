package coder.learning.api.demo.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * Created by Administrator on 2018/5/1.
 */
@Mapper
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    @Mappings({
            @Mapping(source = "noForAir", target = "noForWts")
    })
    FlightForWts mappingFromAirToWts(FlightForAir flight);
}
