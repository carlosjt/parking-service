package py.com.parking.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.VehiclesDTO;
import py.com.parking.models.entities.Vehicles;

@Mapper(uses = {UsersMapper.class})
public interface VehiclesMapper {

    VehiclesMapper INSTANCE = Mappers.getMapper(VehiclesMapper.class);

    Vehicles toEntity(VehiclesDTO vehiclesDTO);

    VehiclesDTO toDTO(Vehicles vehicles);

    void mapDtoToEntity(VehiclesDTO vehiclesDTO, @MappingTarget Vehicles vehicles);
}
