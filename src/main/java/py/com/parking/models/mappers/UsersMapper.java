package py.com.parking.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import py.com.parking.models.dto.UsersDTO;
import py.com.parking.models.entities.Users;

@Mapper
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    Users toEntity(UsersDTO usersDTO);

    UsersDTO toDTO(Users users);

    void mapDtoToEntity(UsersDTO usersDTO, @MappingTarget Users users);
}
