package vn.itz.plansync.mapper;

import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import vn.itz.plansync.dto.PeriodDto;
import vn.itz.plansync.form.PeriodForm;
import vn.itz.plansync.model.Period;

@Mapper(componentModel = "spring")
public interface PeriodMapper {
  @Mappings({
      @Mapping(source = "periodName", target = "name"),
      @Mapping(source = "periodDescription", target = "description"),
      @Mapping(source = "periodStartDate", target = "startDate"),
      @Mapping(source = "periodDueDate", target = "dueDate"),
      @Mapping(source = "periodState", target = "state")
  })
  @Named("mapToPeriod")
  Period convertToPeriod(PeriodForm request);

  @Mappings({
      @Mapping(source = "id", target = "periodId"),
      @Mapping(source = "name", target = "periodName"),
      @Mapping(source = "description", target = "periodDescription"),
      @Mapping(source = "startDate", target = "periodStartDate"),
      @Mapping(source = "dueDate", target = "periodDueDate"),
      @Mapping(source = "state", target = "periodState")
  })
  @Named("mapToPeriodDto")
  PeriodDto convertToPeriodDto(Period request);

  @IterableMapping(elementTargetType = PeriodDto.class, qualifiedByName = "mapToPeriodDto")
  List<PeriodDto> convertToListPeriodDto(List<Period> requests);

  @Mappings({
      @Mapping(source = "periodName", target = "name"),
      @Mapping(source = "periodDescription", target = "description"),
      @Mapping(source = "periodStartDate", target = "startDate"),
      @Mapping(source = "periodDueDate", target = "dueDate"),
      @Mapping(source = "periodState", target = "state")
  })
  void updatePeriod(@MappingTarget Period period, PeriodForm request);
}
