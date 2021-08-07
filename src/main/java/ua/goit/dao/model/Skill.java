package ua.goit.dao.model;

import javax.persistence.*;

@Entity
@Table(name= "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private Integer skill_id;
    @Column(name = "branch")
    private String branch;
    @Column(name = "stage")
    private String stage;

    public Skill(Integer skill_id, String branch, String stage) {
        this.skill_id = skill_id;
        this.branch = branch;
        this.stage = stage;
    }

    public Skill() {
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
