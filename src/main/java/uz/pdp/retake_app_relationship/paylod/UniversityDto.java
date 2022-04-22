package uz.pdp.retake_app_relationship.paylod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UniversityDto {

    private String name;
    private String city;
    private String district;
    private String street;
}
