package vn.itz.plansync.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerSchedulerDto {
  private Long lecturerSchedulerId;
  private Long lecturerIdValue;
  private int courseIdValue;
  private Long periodIdValue;
}
