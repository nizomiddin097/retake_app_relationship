package uz.pdp.retake_app_relationship.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.retake_app_relationship.entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Page<Student> findAllByGroup_Faculty_UniversityId(Integer group_faculty_university_id);


}
