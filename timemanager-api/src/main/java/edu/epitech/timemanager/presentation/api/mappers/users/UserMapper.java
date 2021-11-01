package edu.epitech.timemanager.presentation.api.mappers.users;

import edu.epitech.timemanager.domains.models.User;
import edu.epitech.timemanager.presentation.api.dto.users.UserView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public UserView userToUserView(User source);
    public List<UserView> userListToUserViewList(List<User> source);
}
