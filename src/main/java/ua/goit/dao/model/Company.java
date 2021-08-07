package ua.goit.dao.model;

import javax.persistence.*;

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

    public Company() {
    }

    public Company(Integer company_id, String company_name, String headquarters) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.headquarters = headquarters;
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

    @Override
    public String toString() {
        return  String.format("""
                Идентификатор компании: %s <br>
                Название компании: %s <br>
                Главный офис  компании: %s <br>
                """, company_id, company_name, headquarters);
    }
}
