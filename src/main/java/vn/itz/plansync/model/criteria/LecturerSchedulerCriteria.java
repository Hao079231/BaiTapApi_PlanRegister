package vn.itz.plansync.model.criteria;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import vn.itz.plansync.model.LecturerScheduler;

@Data
public class LecturerSchedulerCriteria {
  private Long id;
  private Long lecturerId;
  private Integer courseId;
  private Integer sortById;
  public Specification<LecturerScheduler> getCriteria(){
    return new Specification<LecturerScheduler>() {
      @Override
      public Predicate toPredicate(Root<LecturerScheduler> root, CriteriaQuery<?> criteriaQuery,
          CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(getLecturerId() != null)
          predicates.add(criteriaBuilder.equal(root.get("lecturerId"), getLecturerId()));

        if(getCourseId() != null)
          predicates.add(criteriaBuilder.equal(root.get("courseId"), getCourseId()));

        if (sortById != null){
          if (sortById == 1) {
            criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
          } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
          }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
      }
    };
  }
}
