package ua.goit.service.converters;

import ua.goit.dao.model.Project;
import ua.goit.dto.ProjectDTO;

import java.util.Set;
import java.util.stream.Collectors;

import static ua.goit.service.converters.CompanyConverter.fromCompanies;
import static ua.goit.service.converters.CompanyConverter.toCompanies;
import static ua.goit.service.converters.CustomerConverter.fromCustomers;
import static ua.goit.service.converters.CustomerConverter.toCustomers;
import static ua.goit.service.converters.DeveloperConverter.fromDevelopers;
import static ua.goit.service.converters.DeveloperConverter.toDevelopers;

public class ProjectConverter {
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

    public static Set<Project> toProjects(Set<ProjectDTO> projects) {
        if (projects!=null){
            return projects.stream()
                    .map(ProjectConverter::toProject)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static Set<ProjectDTO> fromProjects(Set<Project> projects) {
        if (projects!=null){
            return projects.stream()
                    .map(ProjectConverter::fromProject)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static Project toProjectWithoutAssociative(ProjectDTO projectDTO){
        return new Project(projectDTO.getProject_id(), projectDTO.getProject_name(), projectDTO.getProject_description(),
                projectDTO.getCost(), projectDTO.getStart_date());
    }

    public static ProjectDTO fromProjectWithoutAssociative(Project project) {
        return new ProjectDTO(project.getProject_id(), project.getProject_name(), project.getProject_description(),
                project.getCost(), project.getStart_date());
    }
}
