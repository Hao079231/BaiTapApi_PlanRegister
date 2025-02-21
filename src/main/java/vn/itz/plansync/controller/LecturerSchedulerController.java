package vn.itz.plansync.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.itz.plansync.dto.ApiMessageDto;
import vn.itz.plansync.dto.LecturerSchedulerDto;
import vn.itz.plansync.dto.ShowPagedResults;
import vn.itz.plansync.form.lecturerScheduler.LecturerSchedulerCreateForm;
import vn.itz.plansync.form.lecturerScheduler.LecturerSchedulerUpdateForm;
import vn.itz.plansync.model.criteria.LecturerSchedulerCriteria;
import vn.itz.plansync.service.LecturerSchedulerService;
import vn.itz.plansync.utils.ApiMessageUtils;

@RestController
@RequestMapping("/api/lecturer-scheduler")
public class LecturerSchedulerController {

  @Autowired
  private LecturerSchedulerService lecturerSchedulerService;

  @GetMapping("/list")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<ShowPagedResults<LecturerSchedulerDto>>> getAllLecturerScheduler(
      LecturerSchedulerCriteria request, Pageable pageable){
    ApiMessageDto<ShowPagedResults<LecturerSchedulerDto>> response = ApiMessageUtils.results("Danh sach dang ky mon hoc trong ky",
        lecturerSchedulerService.getFilteredLecturerSchedulerDtos(request, pageable));
    return ResponseEntity.ok(response);
  }

  @GetMapping("/get/{id}")
  @PreAuthorize("hasAuthority('C_GET')")
  public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> getLecturerSchedulerById(@PathVariable Long id) {
    ApiMessageDto<LecturerSchedulerDto> response = ApiMessageUtils.results("Thong tin lich trinh cua giang vien",
        lecturerSchedulerService.getLecturerSchedulerById(id));
    return ResponseEntity.ok(response);
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('C_CREATE')")
  public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> createLecturerScheduler(@RequestBody
  LecturerSchedulerCreateForm request){
    ApiMessageDto<LecturerSchedulerDto> response = ApiMessageUtils.results("Dang ky thanh cong",
        lecturerSchedulerService.createLecturerScheduler(request));
    return ResponseEntity.ok(response);
  }

  @PutMapping("/update")
  @PreAuthorize("hasAuthority('C_UPDATE')")
  public ResponseEntity<ApiMessageDto<LecturerSchedulerDto>> updateLecturerScheduler(
      @RequestBody LecturerSchedulerUpdateForm request) {
    ApiMessageDto<LecturerSchedulerDto> response = ApiMessageUtils.results("Cap nhat thanh cong",
        lecturerSchedulerService.updateLecturerScheduler(request));
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAuthority('C_DELETE')")
  public ResponseEntity<ApiMessageDto<String>> deleteLecturerScheduler(@PathVariable Long id) {
    lecturerSchedulerService.deleteLecturerScheduler(id);
    ApiMessageDto<String> response = ApiMessageUtils.results("Xoa thanh cong", null);
    return ResponseEntity.ok(response);
  }
}
