package ua.goit.service.converters;

import ua.goit.dao.model.Skill;
import ua.goit.dto.SkillDTO;

import java.util.Set;
import java.util.stream.Collectors;

import static ua.goit.service.converters.DeveloperConverter.fromDevelopers;
import static ua.goit.service.converters.DeveloperConverter.toDevelopers;

public class SkillConverter {
    public static Skill toSkill(SkillDTO skillDTO) {
        return new Skill(skillDTO.getSkill_id(), skillDTO.getBranch(), skillDTO.getStage(),
                toDevelopers(skillDTO.getDevelopers()));
    }

    public static SkillDTO fromSkill(Skill skill) {
        return new SkillDTO(skill.getSkill_id(), skill.getBranch(), skill.getStage(),
                fromDevelopers(skill.getDevelopers()));
    }

    public static Set<Skill> toSkills(Set<SkillDTO> skills) {
        if (skills!=null){
            return skills.stream()
                    .map(SkillConverter::toSkill)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static Set<SkillDTO> fromSkills(Set<Skill> skills) {
        if (skills!=null){
            return skills.stream()
                    .map(SkillConverter::fromSkill)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static Skill toSkillWithoutAssociative(SkillDTO skillDTO) {
        return new Skill(skillDTO.getSkill_id(), skillDTO.getBranch(), skillDTO.getStage());
    }

    public static SkillDTO fromSkillWithoutAssociative(Skill skill) {
        return new SkillDTO(skill.getSkill_id(), skill.getBranch(), skill.getStage());
    }
}
