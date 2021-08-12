package ua.goit.dto;

import java.util.Set;

import static ua.goit.view.Util.joinSetElements;

public class SkillDTO {
    private Integer skill_id;
    private String branch;
    private String stage;
    private Set<DeveloperDTO> developers;

    public SkillDTO() {
    }

    public SkillDTO(Integer skill_id, String branch, String stage, Set<DeveloperDTO> developers) {
        this.skill_id = skill_id;
        this.branch = branch;
        this.stage = stage;
        this.developers = developers;
    }

    public SkillDTO(Integer skill_id, String branch, String stage) {
        this.skill_id = skill_id;
        this.branch = branch;
        this.stage = stage;
    }

    public String toStringWithAssociative(){
        return this + "<br>" + joinSetElements(developers);
    }

    public Set<DeveloperDTO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDTO> developers) {
        this.developers = developers;
    }

    public Integer getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Integer skill_id) {
        this.skill_id = skill_id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    @Override
    public String toString() {
        return String.format("""
               Идентификатор умения: %s <br>
               Специализация: %s <br>
               Уровень умений: %s <br>
               """, skill_id, branch, stage);
    }
}
