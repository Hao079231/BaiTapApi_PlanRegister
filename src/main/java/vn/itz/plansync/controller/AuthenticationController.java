package vn.itz.plansync.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.plansync.dto.ApiMessageDto;
import vn.itz.plansync.dto.AuthenticationDto;
import vn.itz.plansync.dto.UserDto;
import vn.itz.plansync.form.user.UserCreateForm;
import vn.itz.plansync.service.AuthenticationService;
import vn.itz.plansync.service.CustomUserDetailService;
import vn.itz.plansync.service.JwtService;
import vn.itz.plansync.utils.ApiMessageUtils;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationService authService;

  @Autowired
  private JwtService jwtService;

  @Autowired
  private CustomUserDetailService userDetailService;

  @PostMapping("/register")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<UserDto>> register(@Valid @RequestBody UserCreateForm request) {
    ApiMessageDto<UserDto> response = ApiMessageUtils.results(
        "Dang ky thanh cong",
        authService.register(request)
    );
    return ResponseEntity.ok(response);
  }
}
