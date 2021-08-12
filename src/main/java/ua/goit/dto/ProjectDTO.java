package ua.goit.dto;

import java.time.LocalDate;
import java.util.Set;

import static ua.goit.view.Util.joinSetElements;

public class ProjectDTO {
    private Integer project_id;
    private String project_name;
    private String project_description;
    private Integer cost;
    private LocalDate start_date;
    private Set<CustomerDTO> customers;
    private Set<CompanyDTO> companies;
    private Set<DeveloperDTO> developers;

    public ProjectDTO() {
    }

    public ProjectDTO(Integer project_id, String project_name, String project_description, Integer cost,
                      LocalDate start_date, Set<CustomerDTO> customers, Set<CompanyDTO> companies,
                      Set<DeveloperDTO> developers) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_description = project_description;
        this.cost = cost;
        this.start_date = start_date;
        this.customers = customers;
        this.companies = companies;
        this.developers = developers;
    }

    public ProjectDTO(Integer project_id, String project_name, String project_description, Integer cost,
                      LocalDate start_date) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_description = project_description;
        this.cost = cost;
        this.start_date = start_date;
    }

    public Set<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerDTO> customers) {
        this.customers = customers;
    }

    public Set<CompanyDTO> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyDTO> companies) {
        this.companies = companies;
    }

    public Set<DeveloperDTO> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDTO> developers) {
        this.developers = developers;
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
    public String toString(){
        return String.format("""
                Идентификатор проекта: %s <br>
                Название проета: %s <br>
                Описание проекта: %s <br>
                Стоимость проекта: %s <br>
                Дата начала проекта: %s <br>
                """, project_id, project_name, project_description, cost, start_date);
    }

    public String toStringWithAssociative(String associativeSet) {
        switch (associativeSet) {
            case "customer" -> {
                return this + "<br>" + joinSetElements(customers);
            }
            case "company" -> {
                return this + "<br>" + joinSetElements(companies);
            }
            case "developer" -> {
                return this + "<br>" + joinSetElements(developers);
            }
            default -> {
                return "Error";
            }
        }
    }
}
