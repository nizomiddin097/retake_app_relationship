package uz.pdp.retake_app_relationship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.retake_app_relationship.entity.University;

public interface UniversityRepository extends JpaRepository<University, Integer> {
}
