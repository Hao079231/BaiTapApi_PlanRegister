package vn.itz.plansync.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {
  RESOURCE_NOT_FOUND("Khong tim thay tai nguyen", HttpStatus.NOT_FOUND),
  BAD_REQUEST("Du lieu yeu cau khong hop le", HttpStatus.BAD_REQUEST),
  INTERNAL_SERVER_ERROR("Loi may chu noi bo", HttpStatus.INTERNAL_SERVER_ERROR),
  DUPLICATE_RESOURCE("Tai nguyen da ton tai", HttpStatus.CONFLICT);

  private String message;
  private HttpStatus status;
}
