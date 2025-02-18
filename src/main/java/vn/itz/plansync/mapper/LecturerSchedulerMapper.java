package vn.itz.plansync.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.plansync.dto.LecturerSchedulerDto;
import vn.itz.plansync.form.lecturerScheduler.LecturerSchedulerCreateForm;
import vn.itz.plansync.form.lecturerScheduler.LecturerSchedulerUpdateForm;
import vn.itz.plansync.model.LecturerScheduler;

@Mapper(componentModel = "spring")
public interface LecturerSchedulerMapper {

  @Mappings({
      @Mapping(source = "lecturerIdValue", target = "lecturerId"),
      @Mapping(source = "courseIdValue", target = "courseId"),
      @Mapping(source = "periodId", target = "period.id")
  })
  @Named("mapToLecturerScheduler")
  LecturerScheduler converToLecturerScheduler(LecturerSchedulerCreateForm request);

  @Mappings({
      @Mapping(source = "id", target = "lecturerSchedulerId"),
      @Mapping(source = "lecturerId", target = "lecturerIdValue"),
      @Mapping(source = "courseId", target = "courseIdValue"),
      @Mapping(source = "period.id", target = "periodIdValue")
  })
  @Named("mapToLecturerSchedulerDto")
  LecturerSchedulerDto converToLecturerSchedulerDto(LecturerScheduler request);

  @IterableMapping(elementTargetType = LecturerSchedulerDto.class, qualifiedByName = "mapToLecturerSchedulerDto")
  List<LecturerSchedulerDto> convertToListLecturerSchedulerDto(List<LecturerScheduler> requests);

  @Mappings({
      @Mapping(source = "lecturerIdValue", target = "lecturerId"),
      @Mapping(source = "courseIdValue", target = "courseId")
  })
  void updateLecturerScheduler(@MappingTarget LecturerScheduler lecturerScheduler, LecturerSchedulerUpdateForm request);
}
