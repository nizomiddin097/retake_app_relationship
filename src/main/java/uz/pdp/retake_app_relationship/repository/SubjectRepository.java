package uz.pdp.retake_app_relationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.retake_app_relationship.entity.Subject;


public interface  SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(String name);
}
