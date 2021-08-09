package ua.goit.dto;

import java.util.Set;

public class CustomerDTO {
    private Integer customer_id;
    private String customer_name;
    private Set<CompanyDTO> companies;
    private Set<ProjectDTO> projects;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer customer_id, String customer_name, Set<CompanyDTO> companies, Set<ProjectDTO> projects) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.companies = companies;
        this.projects = projects;
    }

    public Set<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDTO> projects) {
        this.projects = projects;
    }

    public Set<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyDTO> companies) {
        this.companies = companies;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    @Override
    public String toString() {
        return  "Customer_id: " + customer_id +
                "Customer_name: " + customer_name;
    }
}
