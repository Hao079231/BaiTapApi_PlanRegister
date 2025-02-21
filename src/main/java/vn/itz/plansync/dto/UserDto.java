package vn.itz.plansync.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private Long userIdValue;
  private String userNameValue;
  private String fullNameValue;
  private String genderValue;
}
