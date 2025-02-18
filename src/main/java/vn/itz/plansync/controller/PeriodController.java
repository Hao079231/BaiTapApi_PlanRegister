package vn.itz.plansync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.plansync.dto.ApiMessageDto;
import vn.itz.plansync.dto.PeriodDto;
import vn.itz.plansync.dto.ShowPagedResults;
import vn.itz.plansync.form.period.PeriodCreateForm;
import vn.itz.plansync.form.period.PeriodUpdateForm;
import vn.itz.plansync.model.criteria.PeriodCriteria;
import vn.itz.plansync.service.PeriodService;
import vn.itz.plansync.utils.ApiMessageUtils;

@RestController
@RequestMapping("/api/period")
public class PeriodController {

  @Autowired
  private PeriodService periodService;

  @GetMapping("/list")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<PeriodDto>>> getPagedPeriods(
      PeriodCriteria request, Pageable pageable
  ){
    ApiMessageDto<ShowPagedResults<PeriodDto>> response = ApiMessageUtils.results("Danh sach cac ki hoc",
        periodService.getFilteredPeriods(request, pageable));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiMessageDto<PeriodDto>> getPeriodById(@PathVariable Long id) {
    ApiMessageDto<PeriodDto> response = ApiMessageUtils.results("Thong tin ki hoc",
        periodService.getPeriodById(id));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  public ResponseEntity<ApiMessageDto<PeriodDto>> createPeriod(@RequestBody PeriodCreateForm request){
    ApiMessageDto<PeriodDto> response = ApiMessageUtils.results("Tao ki hoc moi thanh cong",
        periodService.createPeriod(request));
    return ResponseEntity.ok(response);
  }

  @PutMapping("/{id}/update")
  public ResponseEntity<ApiMessageDto<PeriodDto>> updatePeriod(@PathVariable Long id,
      @RequestBody PeriodUpdateForm request) {
    ApiMessageDto<PeriodDto> response = ApiMessageUtils.results("Cap nhat ki hoc thanh cong",
        periodService.updatePeriod(id, request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<ApiMessageDto<String>> deletePeriod(@PathVariable Long id) {
    periodService.deletePeriod(id);
    ApiMessageDto<String> response = ApiMessageUtils.results("Xoa ki hoc thanh cong", null);
    return ResponseEntity.ok(response);
  }
}
