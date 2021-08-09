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

    private Set<Customer> customers;
    private Set<Project> projects;

    public Company() {
    }

    public Company(Integer company_id, String company_name, String headquarters, Set<Customer> customers, Set<Project> projects) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
        this.customers = customers;
        this.projects = projects;
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


    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
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
                Идентификатор компании: %s <br>
                Название компании: %s <br>
                Главный офис  компании: %s <br>
                """, company_id, company_name, headquarters);
    }
}
