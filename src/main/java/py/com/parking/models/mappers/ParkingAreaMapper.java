package py.com.parking.models.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.ParkingAreaDTO;
import py.com.parking.models.entities.ParkingArea;

@Mapper
public interface ParkingAreaMapper {

    ParkingAreaMapper INSTANCE = Mappers.getMapper(ParkingAreaMapper.class);

    ParkingArea toEntity(ParkingAreaDTO parkingAreaDTO);

    ParkingAreaDTO toDTO(ParkingArea parkingArea);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(ParkingAreaDTO parkingAreaDTO, @MappingTarget ParkingArea parkingArea);
}
