package ua.goit.service.converters;

import ua.goit.dao.model.Developer;
import ua.goit.dto.DeveloperDTO;

import java.util.Set;
import java.util.stream.Collectors;

import static ua.goit.service.converters.ProjectConverter.fromProjects;
import static ua.goit.service.converters.ProjectConverter.toProjects;
import static ua.goit.service.converters.SkillConverter.fromSkills;
import static ua.goit.service.converters.SkillConverter.toSkills;

public class DeveloperConverter {
    public static Developer toDeveloper(DeveloperDTO developerDTO){
        return new Developer(developerDTO.getDeveloper_id(), developerDTO.getFirst_name(), developerDTO.getLast_name(),
                developerDTO.getGender(), developerDTO.getSalary(), toProjects(developerDTO.getProjects()),
                toSkills(developerDTO.getSkills()));
    }

    public static DeveloperDTO fromDeveloper(Developer developer){
        return new DeveloperDTO(developer.getDeveloper_id(), developer.getFirst_name(), developer.getLast_name(),
                developer.getGender(), developer.getSalary(), fromProjects(developer.getProjects()),
                fromSkills(developer.getSkills()));
    }

    public static Set<Developer> toDevelopers(Set<DeveloperDTO> developers) {
        if (developers!=null){
            return developers.stream()
                    .map(DeveloperConverter::toDeveloperWithoutAssociative)
                    .collect(Collectors.toSet());
        }
        return null;
    }


    public static Set<DeveloperDTO> fromDevelopers(Set<Developer> developers) {
        if (developers!=null){
            return developers.stream()
                    .map(DeveloperConverter::fromDeveloperWithoutAssociative)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static Developer toDeveloperWithoutAssociative(DeveloperDTO developerDTO){
        return new Developer(developerDTO.getDeveloper_id(), developerDTO.getFirst_name(), developerDTO.getLast_name(),
                developerDTO.getGender(), developerDTO.getSalary());
    }

    public static DeveloperDTO fromDeveloperWithoutAssociative(Developer developer){
        return new DeveloperDTO(developer.getDeveloper_id(), developer.getFirst_name(), developer.getLast_name(),
                developer.getGender(), developer.getSalary());
    }
}
