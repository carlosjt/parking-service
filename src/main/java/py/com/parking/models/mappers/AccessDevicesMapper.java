package py.com.parking.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.AccessDevicesDTO;
import py.com.parking.models.entities.AccessDevices;

@Mapper
public interface AccessDevicesMapper {

    AccessDevicesMapper INSTANCE = Mappers.getMapper(AccessDevicesMapper.class);

    AccessDevices toEntity(AccessDevicesDTO accessDevicesDTO);

    AccessDevicesDTO toDTO(AccessDevices accessDevices);

    void mapDtoToEntity(AccessDevicesDTO accessDevicesDTO, @MappingTarget AccessDevices accessDevices);
}
