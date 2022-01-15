package net.javaguides.springboot.mappers;

import net.javaguides.springboot.models.dto.UserDto;
import net.javaguides.springboot.models.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User map(UserDto userDto);
    UserDto map(User user);
    List<UserDto> map(List<User> user);

}
