package vn.itz.plansync.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.itz.plansync.model.LecturerScheduler;

@Repository
public interface LecturerSchedulerRepository extends JpaRepository<LecturerScheduler, Long>,
    JpaSpecificationExecutor<LecturerScheduler> {

}
