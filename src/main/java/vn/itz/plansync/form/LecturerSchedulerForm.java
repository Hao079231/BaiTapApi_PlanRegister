package vn.itz.plansync.form;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerSchedulerForm {
  @NotNull(message = "Id cua giao vien khong the trong")
  private Long lecturerIdValue;

  @NotNull(message = "Id cua khoa hoc khong the trong")
  private int courseIdValue;

  @NotNull(message = "Id cua ki hoc khong the trong")
  private Long periodId;
}
