package vn.itz.plansync.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.itz.plansync.dto.PeriodDto;
import vn.itz.plansync.dto.ShowPagedResults;
import vn.itz.plansync.exception.ResourceNotFound;
import vn.itz.plansync.form.period.PeriodCreateForm;
import vn.itz.plansync.form.period.PeriodUpdateForm;
import vn.itz.plansync.mapper.PeriodMapper;
import vn.itz.plansync.model.Period;
import vn.itz.plansync.model.criteria.PeriodCriteria;
import vn.itz.plansync.repository.PeriodRepository;

@Service
public class PeriodService {

  @Autowired
  private PeriodRepository periodRepository;

  @Autowired
  private PeriodMapper periodMapper;

  @Autowired
  private JwtService jwtService;

  public PeriodDto getPeriodById(Long id) {
    Period period = periodRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Hoc ky khong ton tai"));
    return periodMapper.convertToPeriodDto(period);
  }

  @Transactional
  public PeriodDto createPeriod(PeriodCreateForm request) {
    if (!request.getPeriodDueDate().isAfter(request.getPeriodStartDate())) {
      throw new IllegalArgumentException(
          "Ngay ket thuc hoc ky phai la ngay sau ngay bat dau hoc ky");
    }

    // Lay hoc ki gan nhat
    Optional<Period> lastPeriodOpt = periodRepository.findTopByOrderByStartDateDesc();

    if (lastPeriodOpt.isPresent()) {
      Period lastPeriod = lastPeriodOpt.get();
      if (!request.getPeriodStartDate().isAfter(lastPeriod.getDueDate())) {
        throw new IllegalArgumentException(
            "Ngay bat dau hoc ky moi phai la ngay sau ngay ket thuc cua ky truoc");
      }
    }

    Period newPeriod = periodMapper.convertToPeriod(request);
    Period savedPeriod = periodRepository.save(newPeriod);

    return periodMapper.convertToPeriodDto(savedPeriod);
  }

  @Transactional
  public PeriodDto updatePeriod(PeriodUpdateForm request) {
    Period period = periodRepository.findById(request.getPeriodId())
        .orElseThrow(() -> new ResourceNotFound("Hoc ky khong ton tai"));

    if (!request.getPeriodDueDate().isAfter(request.getPeriodStartDate())) {
      throw new IllegalArgumentException(
          "Ngay ket thuc hoc ky phai la ngay sau ngay bat dau hoc ky");
    }

    periodMapper.updatePeriod(period, request);
    period = periodRepository.save(period);
    return periodMapper.convertToPeriodDto(period);
  }

  @Transactional
  public void deletePeriod(Long id) {
    Period period = periodRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFound("Hoc ky khong ton tai"));
    periodRepository.delete(period);
  }

  // Loc va phan trang lay danh sach hoc ky
  public ShowPagedResults<PeriodDto> getFilteredPeriods(PeriodCriteria request, Pageable pageable) {
    Page<Period> periods = periodRepository.findAll(request.getCriteria(), pageable);
    List<PeriodDto> periodDtos = periodMapper.convertToListPeriodDto(periods.getContent());
    return new ShowPagedResults<>(periodDtos, periods.getTotalElements(), periods.getTotalPages());
  }
}
