package vn.itz.plansync.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.itz.plansync.dto.LecturerSchedulerDto;
import vn.itz.plansync.dto.ShowPagedResults;
import vn.itz.plansync.exception.ResourceNotFound;
import vn.itz.plansync.form.lecturerScheduler.LecturerSchedulerCreateForm;
import vn.itz.plansync.form.lecturerScheduler.LecturerSchedulerUpdateForm;
import vn.itz.plansync.mapper.LecturerSchedulerMapper;
import vn.itz.plansync.model.LecturerScheduler;
import vn.itz.plansync.model.Period;
import vn.itz.plansync.model.criteria.LecturerSchedulerCriteria;
import vn.itz.plansync.repository.LecturerSchedulerRepository;
import vn.itz.plansync.repository.PeriodRepository;

@Service
public class LecturerSchedulerService {
  @Autowired
  private LecturerSchedulerRepository lecturerSchedulerRepository;

  @Autowired
  private PeriodRepository periodRepository;

  @Autowired
  private LecturerSchedulerMapper lecturerSchedulerMapper;

  public List<LecturerSchedulerDto> getAllLecturerScheduler(){
    List<LecturerScheduler> lecturerSchedulers = lecturerSchedulerRepository.findAll();
    return lecturerSchedulerMapper.convertToListLecturerSchedulerDto(lecturerSchedulers);
  }

  public LecturerSchedulerDto getLecturerSchedulerById(Long id){
    LecturerScheduler lecturerScheduler = lecturerSchedulerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Khong tim thay lich trinh giang vien voi ID: " + id));
    return lecturerSchedulerMapper.converToLecturerSchedulerDto(lecturerScheduler);
  }

  @Transactional
  public LecturerSchedulerDto createLecturerScheduler(LecturerSchedulerCreateForm request){
    Period period = periodRepository.findById(request.getPeriodId()).orElseThrow(()
    -> new ResourceNotFound("Khong tim thay hoc ky da chon"));
    LecturerScheduler lecturerScheduler = lecturerSchedulerMapper.converToLecturerScheduler(request);
    return lecturerSchedulerMapper.converToLecturerSchedulerDto(lecturerSchedulerRepository.save(lecturerScheduler));
  }

  @Transactional
  public LecturerSchedulerDto updateLecturerScheduler(Long id, LecturerSchedulerUpdateForm request){
    LecturerScheduler lecturerScheduler = lecturerSchedulerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Khong tim thay lich trinh voi ID: " + id));
    lecturerSchedulerMapper.updateLecturerScheduler(lecturerScheduler, request);
    return lecturerSchedulerMapper.converToLecturerSchedulerDto(lecturerSchedulerRepository.save(lecturerScheduler));
  }

  @Transactional
  public void deleteLecturerScheduler(Long id){
    LecturerScheduler lecturerScheduler = lecturerSchedulerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Khong tim thay lich trinh voi ID: " + id));

    lecturerSchedulerRepository.delete(lecturerScheduler);
  }

  // Loc va phan trang lay danh sach lich trinh
  public ShowPagedResults<LecturerSchedulerDto> getFilteredLecturerSchedulerDtos(LecturerSchedulerCriteria request, Pageable pageable){
    Page<LecturerScheduler> lecturerSchedulers = lecturerSchedulerRepository.findAll(request.getCriteria(), pageable);
    List<LecturerSchedulerDto> lecturerSchedulerDtos =lecturerSchedulerMapper.convertToListLecturerSchedulerDto(lecturerSchedulers.getContent());
    return new ShowPagedResults<>(lecturerSchedulerDtos, lecturerSchedulers.getTotalElements(), lecturerSchedulers.getTotalPages());
  }
}
