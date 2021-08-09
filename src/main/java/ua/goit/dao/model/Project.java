package ua.goit.dao.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Integer project_id;
    @Column(name = "project_name")
    private String project_name;
    @Column(name = "project_description")
    private String project_description;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "start_date")
    private LocalDate start_date;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ua.goit.dao.model.Customer.class)
    @JoinTable(
            name = "project_customers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private Set<Customer> customers;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ua.goit.dao.model.Company.class)
    @JoinTable(
            name = "company_projects",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private Set<Company> companies;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ua.goit.dao.model.Developer.class)
    @JoinTable(
            name = "project_developers",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "developer_id"))
    private Set<Developer> developers;

    public Project() {
    }

    public Project(Integer project_id, String project_name, String project_description, Integer cost,
                   LocalDate start_date, Set<Customer> customers, Set<Company> companies, Set<Developer> developers) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_description = project_description;
        this.cost = cost;
        this.start_date = start_date;
        this.customers = customers;
        this.companies = companies;
        this.developers = developers;
    }

    public Set<Developer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return project_id.equals(project.project_id) && project_name.equals(project.project_name) && project_description.equals(project.project_description) && cost.equals(project.cost) && start_date.equals(project.start_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project_id, project_name, project_description, cost, start_date);
    }

    @Override
    public String toString(){
        return String.format("""
                Идентификатор проекта: %s <br>
                Название проета: %s <br>
                Описание проекта: %s <br>
                Стоимость проекта: %s <br>
                Дата начала проекта: %s <br>
                """, project_id, project_name, project_description, cost, start_date);
    }
}
