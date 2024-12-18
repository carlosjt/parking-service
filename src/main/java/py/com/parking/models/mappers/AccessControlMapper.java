package py.com.parking.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.AccessControlDTO;
import py.com.parking.models.entities.AccessControl;

@Mapper(uses = {VehiclesMapper.class})
public interface AccessControlMapper {

    AccessControlMapper INSTANCE = Mappers.getMapper(AccessControlMapper.class);

    AccessControl toEntity(AccessControlDTO accessControlDTO);

    AccessControlDTO toDTO(AccessControl accessControl);

    void mapDtoToEntity(AccessControlDTO accessControlDTO, @MappingTarget AccessControl accessControl);
}
