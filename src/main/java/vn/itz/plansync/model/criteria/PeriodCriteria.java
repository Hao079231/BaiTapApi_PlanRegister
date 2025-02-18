package vn.itz.plansync.model.criteria;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;
import vn.itz.plansync.model.Period;

@Data
public class PeriodCriteria {
  private Long id;
  private String name;
  private Integer sortById;
  public Specification<Period> getCriteria(){
    return new Specification<Period>() {
      @Override
      public Predicate toPredicate(Root<Period> root, CriteriaQuery<?> criteriaQuery,
          CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if(getName() != null || !getName().isEmpty())
          predicates.add(criteriaBuilder.like(root.get("name"), "%" + getName().toLowerCase() + "%"));

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
