package ua.goit.service;

import ua.goit.dao.model.*;
import ua.goit.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

public class Converter {
    public static Company toCompany(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters(),
                toCustomers(companyDTO.getCustomers()), toProjects(companyDTO.getProjects()));
    }

    public static CompanyDTO fromCompany(Company company){
        return new CompanyDTO(company.getCompany_id(), company.getCompany_name(), company.getHeadquarters(),
                fromCustomers(company.getCustomers()), fromProjects(company.getProjects()));
    }

    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name(),
                toCompanies(customerDTO.getCompanies()), toProjects(customerDTO.getProjects()));
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name(),
                fromCompanies(customer.getCompanies()), fromProjects(customer.getProjects()));
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

    public static LinkedList<Link> toLink(ResultSet resultSet, String table){
        try{
            LinkedList<Link> links = new LinkedList<>();
            if (table.equalsIgnoreCase("customers_companies")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(null);
                    link.setSkill_id(null);
                    link.setCompany_id(resultSet.getInt("company_id"));
                    link.setCustomer_id(resultSet.getInt("customer_id"));
                    link.setProject_id(resultSet.getInt("project_id"));

                    links.addLast(link);
                }
            }
            if (table.equalsIgnoreCase("project_developers")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(null);
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(resultSet.getInt("project_id"));

                    links.addLast(link);
                }
            }
            if (table.equalsIgnoreCase("developer_skills")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(resultSet.getInt("skill_id"));
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(null);
                    links.addLast(link);
                }
            }
            return links;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public static LinkDTO fromLink(Link link){
        return new LinkDTO(link.getTable(), link.getProject_id(), link.getCustomer_id(),
                link.getDeveloper_id(), link.getCompany_id(), link.getSkill_id());
    }


    private static Set<DeveloperDTO> fromDevelopers(Set<Developer> developers) {
        return developers.stream()
                .map(Converter::fromDeveloper)
                .collect(Collectors.toSet());
    }

    private static Set<Developer> toDevelopers(Set<DeveloperDTO> developers) {
        return developers.stream()
                .map(Converter::toDeveloper)
                .collect(Collectors.toSet());
    }

    private static Set<CompanyDTO> fromCompanies(Set<Company> companies) {
        return companies.stream()
                .map(Converter::fromCompany)
                .collect(Collectors.toSet());
    }

    private static Set<Company> toCompanies(Set<CompanyDTO> companies) {
        return companies.stream()
                .map(Converter::toCompany)
                .collect(Collectors.toSet());
    }

    private static Set<SkillDTO> fromSkills(Set<Skill> skills) {
        return skills.stream()
                .map(Converter::fromSkill)
                .collect(Collectors.toSet());
    }

    private static Set<Skill> toSkills(Set<SkillDTO> skills) {
        return skills.stream()
                .map(Converter::toSkill)
                .collect(Collectors.toSet());
    }

    private static Set<ProjectDTO> fromProjects(Set<Project> projects) {
        return projects.stream()
                .map(Converter::fromProject)
                .collect(Collectors.toSet());
    }

    private static Set<Project> toProjects(Set<ProjectDTO> projects) {
        return projects.stream()
                .map(Converter::toProject)
                .collect(Collectors.toSet());
    }

    private static Set<CustomerDTO> fromCustomers(Set<Customer> customers) {
        return customers.stream()
                .map(Converter::fromCustomer)
                .collect(Collectors.toSet());
    }

    private static Set<Customer> toCustomers(Set<CustomerDTO> customers) {
        return customers.stream()
                .map(Converter::toCustomer)
                .collect(Collectors.toSet());
    }
}
