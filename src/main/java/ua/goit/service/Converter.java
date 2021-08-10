package ua.goit.service;

import ua.goit.dao.model.*;
import ua.goit.dto.*;

import java.util.Set;
import java.util.stream.Collectors;

public class Converter {
    public static Company toCompany(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters(), toProjects(companyDTO.getProjects()));
    }

    public static CompanyDTO fromCompany(Company company){
        return new CompanyDTO(company.getCompany_id(), company.getCompany_name(), company.getHeadquarters(), fromProjects(company.getProjects()));
    }

    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name(), toProjects(customerDTO.getProjects()));
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name(), fromProjects(customer.getProjects()));
    }

    public static Developer toDeveloper(DeveloperDTO developerDTO){
        return new Developer(developerDTO.getDeveloper_id(), developerDTO.getFirst_name(), developerDTO.getLast_name(),
                developerDTO.getGender(), developerDTO.getSalary(), toProjects(developerDTO.getProjects()),
                toSkills(developerDTO.getSkills()));
    }

    public static DeveloperDTO fromDeveloper(Developer developer){
        return new DeveloperDTO(developer.getDeveloper_id(), developer.getFirst_name(), developer.getLast_name(),
                developer.getGender(), developer.getSalary(), fromProjects(developer.getProjects()),
                fromSkills(developer.getSkills()));
    }

    public static Project toProject(ProjectDTO projectDTO){
        return new Project(projectDTO.getProject_id(), projectDTO.getProject_name(), projectDTO.getProject_description(),
                projectDTO.getCost(), projectDTO.getStart_date(), toCustomers(projectDTO.getCustomers()),
                toCompanies(projectDTO.getCompanies()), toDevelopers(projectDTO.getDevelopers()));
    }

    public static ProjectDTO fromProject(Project project) {
        return new ProjectDTO(project.getProject_id(), project.getProject_name(), project.getProject_description(),
                project.getCost(), project.getStart_date(), fromCustomers(project.getCustomers()),
                fromCompanies(project.getCompanies()), fromDevelopers(project.getDevelopers()));
    }

    public static Skill toSkill(SkillDTO skillDTO) {
        return new Skill(skillDTO.getSkill_id(), skillDTO.getBranch(), skillDTO.getStage(),
                toDevelopers(skillDTO.getDevelopers()));
    }

    public static SkillDTO fromSkill(Skill skill) {
        return new SkillDTO(skill.getSkill_id(), skill.getBranch(), skill.getStage(),
                fromDevelopers(skill.getDevelopers()));
    }

    private static Set<DeveloperDTO> fromDevelopers(Set<Developer> developers) {
        if (developers!=null){
            return developers.stream()
                    .map(Converter::fromDeveloper)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<Developer> toDevelopers(Set<DeveloperDTO> developers) {
        if (developers!=null){
            return developers.stream()
                    .map(Converter::toDeveloper)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<CompanyDTO> fromCompanies(Set<Company> companies) {
        if (companies!=null){
            return companies.stream()
                    .map(Converter::fromCompany)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<Company> toCompanies(Set<CompanyDTO> companies) {
        if (companies!=null){
            return companies.stream()
                    .map(Converter::toCompany)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<SkillDTO> fromSkills(Set<Skill> skills) {
        if (skills!=null){
            return skills.stream()
                    .map(Converter::fromSkill)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<Skill> toSkills(Set<SkillDTO> skills) {
        if (skills!=null){
            return skills.stream()
                    .map(Converter::toSkill)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<ProjectDTO> fromProjects(Set<Project> projects) {
        if (projects!=null){
            return projects.stream()
                    .map(Converter::fromProject)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<Project> toProjects(Set<ProjectDTO> projects) {
        if (projects!=null){
            return projects.stream()
                    .map(Converter::toProject)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<CustomerDTO> fromCustomers(Set<Customer> customers) {
        if (customers!=null){
            return customers.stream()
                    .map(Converter::fromCustomer)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Set<Customer> toCustomers(Set<CustomerDTO> customers) {
        if (customers!=null) {
            return customers.stream()
                    .map(Converter::toCustomer)
                    .collect(Collectors.toSet());
        }
        return null;
    }
}
