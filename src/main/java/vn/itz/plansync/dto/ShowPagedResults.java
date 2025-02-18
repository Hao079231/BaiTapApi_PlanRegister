package vn.itz.plansync.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ShowPagedResults<T> {
  private List<T> content;
  private long totalElements;
  private int totalPages;
}
