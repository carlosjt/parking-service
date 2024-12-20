package py.com.parking.models.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.ParkingReservationDTO;
import py.com.parking.models.entities.ParkingReservation;

@Mapper(uses = {UsersMapper.class, ParkingAreaMapper.class, SpecialEventMapper.class})
public interface ParkingReservationMapper {
    ParkingReservationMapper INSTANCE = Mappers.getMapper(ParkingReservationMapper.class);

    ParkingReservation toEntity(ParkingReservationDTO parkingReservationDTO);

    ParkingReservationDTO toDTO(ParkingReservation parkingReservation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(ParkingReservationDTO parkingReservationDTO, @MappingTarget ParkingReservation parkingReservation);
}
