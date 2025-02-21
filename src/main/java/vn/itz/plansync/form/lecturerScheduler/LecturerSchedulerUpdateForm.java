package vn.itz.plansync.form.lecturerScheduler;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerSchedulerUpdateForm {
  @ApiModelProperty(value = "Id lich trinh", example = "1", required = true)
  @NotNull(message = "Id lich trinh khong the trong")
  private Long lecturerSchedulerIdValue;

  @ApiModelProperty(value = "Id cua giao vien", example = "1", required = true)
  @NotNull(message = "Id cua giao vien khong the trong")
  private Long lecturerIdValue;

  @ApiModelProperty(value = "Id cua khoa hoc", example = "1", required = true)
  @NotNull(message = "Id cua khoa hoc khong the trong")
  private int courseIdValue;
}
