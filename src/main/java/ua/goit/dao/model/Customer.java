package ua.goit.dao.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer customer_id;
    @Column(name = "customer_name")
    private String customer_name;

    private Set<Company> companies;
    private Set<Project> projects;

    public Customer() {
    }

    public Customer(Integer customer_id, String customer_name, Set<Company> companies, Set<Project> projects) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.companies = companies;
        this.projects = projects;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customer_id.equals(customer.customer_id) && customer_name.equals(customer.customer_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer_id, customer_name);
    }

    @Override
    public String toString(){
        return String.format("""
                Идентификатор заказчика: %s <br>
                Имя заказчика: %s <br>
                """, customer_id, customer_name);
    }
}
