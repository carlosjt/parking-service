package py.com.parking.models.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.ReservationDTO;
import py.com.parking.models.entities.Reservation;
@Mapper
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation toEntity(ReservationDTO reservationDTO);

    ReservationDTO toDTO(Reservation reservation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(ReservationDTO reservationDTO, @MappingTarget Reservation reservation);
}
