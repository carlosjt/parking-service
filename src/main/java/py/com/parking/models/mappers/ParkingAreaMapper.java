package py.com.parking.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.ParkingAreaDTO;
import py.com.parking.models.entities.ParkingArea;

@Mapper
public interface ParkingAreaMapper {

    ParkingAreaMapper INSTANCE = Mappers.getMapper(ParkingAreaMapper.class);

    ParkingArea toEntity(ParkingAreaDTO parkingAreaDTO);

    ParkingAreaDTO toDTO(ParkingArea parkingArea);

    void mapDtoToEntity(ParkingAreaDTO parkingAreaDTO, @MappingTarget ParkingArea parkingArea);
}
