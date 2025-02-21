package vn.itz.plansync.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.itz.plansync.dto.AuthenticationDto;
import vn.itz.plansync.dto.UserDto;
import vn.itz.plansync.exception.ResourceNotFound;
import vn.itz.plansync.form.user.UserCreateForm;
import vn.itz.plansync.mapper.UserMapper;
import vn.itz.plansync.model.Role;
import vn.itz.plansync.model.User;
import vn.itz.plansync.repository.RoleRepository;
import vn.itz.plansync.repository.UserRepository;
import vn.itz.plansync.security.CustomUserDetails;

@Service
public class AuthenticationService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Autowired
  private UserMapper userMapper;

  @Transactional
  public UserDto register(UserCreateForm request) {
    Role role = roleRepository.findRoleByKind(request.getRoleValue()).orElseThrow(()
        -> new ResourceNotFound("Vai tro nay khong ton tai"));

    User user = userMapper.convertToUser(request);
    user.setAvatar("ImageEmpty");
    user.setPassword(passwordEncoder.encode(request.getPassWordValue()));
    user.setRole(role);
    return userMapper.convertToUserResponse(userRepository.save(user));
  }
}
