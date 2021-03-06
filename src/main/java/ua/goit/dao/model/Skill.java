package ua.goit.dao.model;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = ua.goit.dao.model.Developer.class,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "developer_skills",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developers;

    public Skill() {
    }

    public Skill(Integer skill_id, String branch, String stage, Set<Developer> developers) {
        this.skill_id = skill_id;
        this.branch = branch;
        this.stage = stage;
        this.developers = developers;
    }

    public Skill(Integer skill_id, String branch, String stage) {
        this.skill_id = skill_id;
        this.branch = branch;
        this.stage = stage;
    }

    public void addDeveloper(Developer developer) {
        this.developers.add(developer);
        developer.getSkills().add(this);
    }

    public void removeDeveloper(Developer developer) {
        this.developers.remove(developer);
        developer.getSkills().remove(this);
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public synchronized void setDevelopers(Set<Developer> developers) {
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
               ?????????????????????????? ????????????: %s <br>
               ??????????????????????????: %s <br>
               ?????????????? ????????????: %s <br>
               """, skill_id, branch, stage);
    }
}
