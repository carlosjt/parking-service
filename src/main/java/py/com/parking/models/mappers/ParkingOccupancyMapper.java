package py.com.parking.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.ParkingOccupancyDTO;
import py.com.parking.models.entities.ParkingOccupancy;

@Mapper(uses = {ParkingAreaMapper.class, VehiclesMapper.class})
public interface ParkingOccupancyMapper {

    ParkingOccupancyMapper INSTANCE = Mappers.getMapper(ParkingOccupancyMapper.class);

    ParkingOccupancy toEntity(ParkingOccupancyDTO parkingOccupancyDTO);

    ParkingOccupancyDTO toDTO(ParkingOccupancy parkingOccupancy);

    void mapDtoToEntity(ParkingOccupancyDTO parkingOccupancyDTO, @MappingTarget ParkingOccupancy parkingOccupancy);
}
