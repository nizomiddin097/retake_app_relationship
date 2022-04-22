package uz.pdp.retake_app_relationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.retake_app_relationship.entity.Faculty;
import uz.pdp.retake_app_relationship.entity.University;
import uz.pdp.retake_app_relationship.paylod.FacultyDto;
import uz.pdp.retake_app_relationship.repository.FacultyRepository;
import uz.pdp.retake_app_relationship.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UniversityRepository universityRepository;

    //VAZIRLIK UCHUN
    @GetMapping("/read")
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();

    }


    //UNIVERSITET XODIMI UCHUN
    @GetMapping("/read/byUniversityId/{universityId}")
    public List<Faculty> getFacultiesByUniversityId(@PathVariable Integer universityId) {
        List<Faculty> allByUniversityId = facultyRepository.findAllByUniversityId(universityId);
        return allByUniversityId;
    }

    @PostMapping("/add")
    public String addFaculty(@RequestBody FacultyDto facultyDto) {
        boolean exists = facultyRepository.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());

        if (exists)
            return "This university such faculty exists";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isPresent()) {

            faculty.setUniversity(optionalUniversity.get());
            facultyRepository.save(faculty);
            return "Faculty saved";
        }
        return "University not found";


    }
//
//        @PutMapping("/edit")
//    public String editFaculty(@PathVariable Integer universityId,@RequestBody  FacultyDto facultyDto){
//          boolean exists = facultyRepository.existsByNameAndUniversityId(universityId);
//
//
//        }
    @PutMapping("/edit/{id}")
    public String editFaculty(@PathVariable Integer id, @RequestBody FacultyDto facultyDto){
        Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
        if (optionalFaculty.isPresent()){
            Faculty faculty = optionalFaculty.get();
            faculty.setName(facultyDto.getName());
            Optional<University> optionalUniversity = universityRepository.findById(facultyDto.getUniversityId());
            if (!optionalUniversity.isPresent()){
                return "University not found";
            }
            faculty.setUniversity(optionalUniversity.get());

            facultyRepository.save(faculty);
            return "Faculty saved";
        }
        return "Faculty not found";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
        facultyRepository.deleteById(id);
        return "Faculty deleted";
    }catch (Exception e){
            return "Error in deleting";
        }
    }
}
