package uz.pdp.retake_app_relationship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.retake_app_relationship.entity.Address;
import uz.pdp.retake_app_relationship.entity.University;
import uz.pdp.retake_app_relationship.paylod.UniversityDto;
import uz.pdp.retake_app_relationship.repository.AddressRepository;
import uz.pdp.retake_app_relationship.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class UniversityController {
    @Autowired
    UniversityRepository universityRepository;

    @Autowired
    AddressRepository addressRepository;


    //READ
    @GetMapping( "/read")
    public List<University> getUniversities() {
        List<University> universityList = universityRepository.findAll();
        return universityList;
    }

    //CREATE
    @PostMapping( "/add")
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Address address = new Address();
        address.setCity(universityDto.getCity());
        address.setDistrict(universityDto.getDistrict());
        address.setStreet(universityDto.getStreet());
        Address saveAddress = addressRepository.save(address);


        University university = new University();
        university.setName(universityDto.getName());

        university.setAddress(saveAddress);

        universityRepository.save(university);

        return " University saved";
    }


    @PutMapping("/edit/{id}")
    public String editUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> optionalUniversity = universityRepository.findById(id);
        if (optionalUniversity.isPresent()){

            University university = optionalUniversity.get();
            university.setName(universityDto.getName());

            // UNIVERSITETNI BAZADAN KELGAN MAVJUT ADDRESSI
            Address address = university.getAddress();

            address.setCity(universityDto.getCity());
            address.setDistrict(universityDto.getDistrict());
            address.setStreet(universityDto.getStreet());

            addressRepository.save(address);
            universityRepository.save(university);
            return "University edited";
        }
        return "University not found";
    }



    @DeleteMapping("/delete/{id}")
    public String deleteUniversity(@PathVariable Integer id) {
        universityRepository.deleteById(id);
        return " University deleted";
    }




}



