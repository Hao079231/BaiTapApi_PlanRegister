package vn.itz.plansync.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodDto {
  private Long periodId;
  private String periodName;
  private String periodDescription;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate periodStartDate;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate periodDueDate;
  private Integer periodState;
}
