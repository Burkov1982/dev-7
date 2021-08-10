package ua.goit.dao.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "developer_id")
    private Integer developer_id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "gender")
    private String gender;
    @Column(name = "salary")
    private Integer salary;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ua.goit.dao.model.Project.class)
    @JoinTable(
            name = "project_developers",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ua.goit.dao.model.Skill.class)
    @JoinTable(
            name = "developer_skills",
            joinColumns = @JoinColumn(name = "developer_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    public Developer() {
    }

    public Developer(Integer developer_id, String first_name, String last_name, String gender, Integer salary,
                     Set<Project> projects, Set<Skill> skills) {
        this.developer_id = developer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.salary = salary;
        this.projects = projects;
        this.skills = skills;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public synchronized void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public synchronized void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return developer_id.equals(developer.developer_id) && first_name.equals(developer.first_name) && last_name.equals(developer.last_name) && gender.equals(developer.gender) && salary.equals(developer.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hash(developer_id, first_name, last_name, gender, salary);
    }

    @Override
    public String toString(){
        return String.format("""
                Идентификатор разработчика: %d <br>
                Имя разработчика: %s <br>
                Фамилия разработчика: %s <br>
                Пол разработчика: %s <br>
                Зарплата разработчика: %d <br>
                """, developer_id, first_name, last_name, gender, salary);
    }
}
