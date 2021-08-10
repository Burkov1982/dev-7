package ua.goit.dto;

import java.util.Set;

public class CompanyDTO {
    private Integer company_id;
    private String company_name;
    private String headquarters;
    private Set<ProjectDTO> projects;

    public CompanyDTO() {
    }

    public CompanyDTO(Integer company_id, String company_name, String headquarters, Set<ProjectDTO> projects) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
        this.projects = projects;
    }

    public CompanyDTO(Integer company_id, String company_name, String headquarters) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
    }

    public Set<ProjectDTO> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDTO> projects) {
        this.projects = projects;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
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

    @Override
    public String toString(){
        return "Company id: " + company_id
                + "Company name: " + company_name
                + "Company headquarters:" + headquarters;
    }
}
