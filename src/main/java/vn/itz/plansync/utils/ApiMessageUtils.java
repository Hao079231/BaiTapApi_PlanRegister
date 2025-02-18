package vn.itz.plansync.utils;

import org.springframework.http.HttpStatus;
import vn.itz.plansync.dto.ApiMessageDto;

public class ApiMessageUtils {
  public static <T> ApiMessageDto<T> results(String message, T data){
    ApiMessageDto<T> respone = new ApiMessageDto<>();
    respone.setResult(true);
    respone.setMessage(message);
    respone.setData(data);
    respone.setStatus(HttpStatus.OK);
    return respone;
  }
}
