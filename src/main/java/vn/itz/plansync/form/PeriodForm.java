package vn.itz.plansync.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodForm {
  private Long periodId;

  @NotEmpty(message = "Ten ki khong the trong")
  private String periodName;

  private String periodDescription;

  @NotEmpty(message = "Ngay bat dau ki hoc khong the trong")
  @Future(message = "Ngay bat dau ki hoc phai la ngay o tuong lai")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate periodStartDate;

  @Future(message = "Ngay ket thuc ki hoc phai la ngay o tuong lai")
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate periodDueDate;

  @NotNull(message = "Trang thai cua ki khong duoc de trong")
  private Integer periodState;
}
