package uz.pdp.retake_app_relationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.retake_app_relationship.entity.Faculty;
import uz.pdp.retake_app_relationship.entity.Group;
import uz.pdp.retake_app_relationship.paylod.GroupDto;
import uz.pdp.retake_app_relationship.repository.FacultyRepository;
import uz.pdp.retake_app_relationship.repository.GroupRepository;
import uz.pdp.retake_app_relationship.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping("/read")
    public List<Group> getGroups() {
        List<Group> groups = groupRepository.findAll();
        return groups;
    }

    //UNIVERSITET XODIMI UCHUN
    @GetMapping("/read/byUniversityId/{universityId}")
    public List<Group> getGroupsByUniversityid(@PathVariable Integer universityId){

        //BU QUERYLARNI 3 LASINI HAM NATIJASI VA VAZIFASI BIR XIL

//        List<Group> groupsByUniversityId = groupRepository.getGroupsByUniversityId(universityId);
//
//        List<Group> groupsByUniversityIdNative = groupRepository.getGroupsByUniversityIdNative(universityId);

        return groupRepository.findAllByFaculty_University_id(universityId);

    }

    @PostMapping("/add")
    public String addGroup(@RequestBody GroupDto groupDto){
        Group group = new Group();
        group.setName(groupDto.getName());

        Optional<Faculty> optionalFaculty = facultyRepository.findById(groupDto.getFacultyId());
        if (optionalFaculty.isPresent()) {

            group.setFaculty(optionalFaculty.get());

            groupRepository.save(group);
            return "Group added";
        }
        return "Such faculty not found";
    }

}
