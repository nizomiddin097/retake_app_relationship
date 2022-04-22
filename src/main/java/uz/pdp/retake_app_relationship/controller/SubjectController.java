package uz.pdp.retake_app_relationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.retake_app_relationship.entity.Subject;
import uz.pdp.retake_app_relationship.paylod.SubjectDto;
import uz.pdp.retake_app_relationship.repository.SubjectRepository;

import java.util.Currency;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/read")
    public List<Subject> getSubjects(){
        List<Subject> subjects = subjectRepository.findAll();
        return subjects;
    }

   @PostMapping("/add")
    public String addSubject(@RequestBody Subject subject) {
       boolean existByName = subjectRepository.existsByName(subject.getName());
       if (existByName){
           return "Subject already exist";
       }
       subjectRepository.save(subject);
       return "Subject added";
   }


    @PutMapping("/edit/{id}")
    public String editSubjects(@PathVariable Integer id, @RequestBody Subject subject){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
      if (optionalSubject.isPresent()){
          boolean existByName = subjectRepository.existsByName(subject.getName());
          if (existByName){
              return "Subject already exist";
          }
          Subject subject1 = optionalSubject.get();
          subject1.setName(subject.getName());
          subjectRepository.save(subject);
          return "Subject was edited";
      }
      return "Subject was not found";

      }


      @DeleteMapping("/delete/{id}")
    public  String deleteSubjects(@PathVariable Integer id){
          Optional<Subject> optionalSubject = subjectRepository.findById(id);
          if (optionalSubject.isPresent()){
              subjectRepository.deleteById(id);
              return "Subject was deleted";
          }
          return "Subject was not found";

      }

}
