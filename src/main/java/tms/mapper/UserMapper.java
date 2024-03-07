package tms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tms.dto.RegUserDTO;
import tms.entity.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstname", source = "firstname")
    @Mapping(target = "lastname", source = "lastname")
    @Mapping(target = "age", source = "age")
    @Mapping(target = "phoneNumber", source = "phoneNumber")
    User regUserDtoToUser(RegUserDTO regUserDTO);
}
