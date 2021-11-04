package edu.epitech.timemanager.domains.mappers;

import edu.epitech.timemanager.domains.dto.UserDto;
import edu.epitech.timemanager.domains.models.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMappers {
    UserDto userToUserDto(User user);
    List<UserDto> usersToUserDtos(List<User> users);
}
