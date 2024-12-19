package py.com.parking.models.mappers;

import jakarta.enterprise.context.ApplicationScoped;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.ParkingAreaDTO;
import py.com.parking.models.dto.ReservationDTO;
import py.com.parking.models.dto.UsersDTO;
import py.com.parking.models.entities.ParkingArea;
import py.com.parking.models.entities.Reservation;
import py.com.parking.models.entities.Users;

@ApplicationScoped
@Mapper(componentModel = "cdi")

public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    Reservation toEntity(ReservationDTO reservationDTO);

    ReservationDTO toDTO(Reservation reservation);

    ParkingArea toEntity(ParkingAreaDTO parkingAreaDTO);

    ParkingAreaDTO toDTO(ParkingArea parkingArea);

    Users toEntity(UsersDTO usersDTO);

    UsersDTO toDTO(Users user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mapDtoToEntity(ReservationDTO reservationDTO, @MappingTarget Reservation reservation);
}
