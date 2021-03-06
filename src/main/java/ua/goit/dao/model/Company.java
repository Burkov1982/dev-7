package ua.goit.dao.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "companies")
public class Company{
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Integer company_id;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "headquarters")
    private String headquarters;

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = ua.goit.dao.model.Project.class,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(
            name = "company_projects",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    public Company() {
    }

    public Company(Integer company_id, String company_name, String headquarters, Set<Project> projects) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
        this.projects = projects;
    }

    public Company(Integer company_id, String company_name, String headquarters) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
    }

    public void addProject(Project project) {
        this.projects.add(project);
        project.getCompanies().add(this);
    }

    public void removeProject(Project project) {
        this.projects.remove(project);
        project.getCompanies().remove(this);
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public Company setCompany_id(Integer company_id) {
        this.company_id = company_id;
        return this;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public void setHeadquarters(String headquarters) {
        this.headquarters = headquarters;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public synchronized void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return company_id.equals(company.company_id) && company_name.equals(company.company_name) && headquarters.equals(company.headquarters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(company_id, company_name, headquarters);
    }

    @Override
    public String toString() {
        return  String.format("""
                ?????????????????????????? ????????????????: %s <br>
                ???????????????? ????????????????: %s <br>
                ?????????????? ????????  ????????????????: %s <br>
                """, company_id, company_name, headquarters);
    }
}
