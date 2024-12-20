package py.com.parking.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.SpecialEventDTO;
import py.com.parking.models.entities.SpecialEvent;

@Mapper
public interface SpecialEventMapper {

    SpecialEventMapper INSTANCE = Mappers.getMapper(SpecialEventMapper.class);

    SpecialEvent toEntity(SpecialEventDTO specialEventDTO);

    SpecialEventDTO toDTO(SpecialEvent specialEvent);

    void mapDtoToEntity(SpecialEventDTO specialEventDTO, @MappingTarget SpecialEvent specialEvent);
}
