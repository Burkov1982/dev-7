package ua.goit.config.converters;

import ua.goit.dao.model.Company;
import ua.goit.dto.CompanyDTO;

import java.util.Set;
import java.util.stream.Collectors;

import static ua.goit.config.converters.ProjectConverter.fromProjects;
import static ua.goit.config.converters.ProjectConverter.toProjects;

public class CompanyConverter {
    public static Company toCompany(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters(),
                toProjects(companyDTO.getProjects()));
    }

    public static CompanyDTO fromCompany(Company company){
        return new CompanyDTO(company.getCompany_id(), company.getCompany_name(), company.getHeadquarters(),
                fromProjects(company.getProjects()));
    }
    public static Set<Company> toCompanies(Set<CompanyDTO> companies) {
        if (companies!=null){
            return companies.stream()
                    .map(CompanyConverter::toCompanyWithoutAssociative)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    private static Company toCompanyWithoutAssociative(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters());
    }

    private static CompanyDTO fromCompanyWithoutAssociative(Company company){
        return new CompanyDTO(company.getCompany_id(), company.getCompany_name(), company.getHeadquarters());
    }

    public static Set<CompanyDTO> fromCompanies(Set<Company> companies) {
        if (companies!=null){
            return companies.stream()
                    .map(CompanyConverter::fromCompanyWithoutAssociative)
                    .collect(Collectors.toSet());
        }
        return null;
    }
}
