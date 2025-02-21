package vn.itz.plansync.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.plansync.dto.UserDto;
import vn.itz.plansync.form.user.UserCreateForm;
import vn.itz.plansync.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
  @Mappings({
      @Mapping(source = "userNameValue", target = "username"),
      @Mapping(source = "fullNameValue", target = "fullname"),
      @Mapping(source = "passWordValue", target = "password"),
      @Mapping(source = "genderValue", target = "gender")
  })
  User convertToUser(UserCreateForm requestDto);

  @Mappings({
      @Mapping(source = "id", target = "userIdValue"),
      @Mapping(source = "username", target = "userNameValue"),
      @Mapping(source = "fullname", target = "fullNameValue"),
      @Mapping(source = "gender", target = "genderValue")

  })
  @Named("mapUserDto")
  UserDto convertToUserResponse(User user);

  @IterableMapping(elementTargetType = UserDto.class, qualifiedByName = "mapUserDto")
  List<UserDto> convertToListUserResponse(List<User> users);
}
