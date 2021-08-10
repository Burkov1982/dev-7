package ua.goit.service.converters;

import ua.goit.dao.model.Customer;
import ua.goit.dto.CustomerDTO;

import java.util.Set;
import java.util.stream.Collectors;

import static ua.goit.service.converters.ProjectConverter.fromProjects;
import static ua.goit.service.converters.ProjectConverter.toProjects;

public class CustomerConverter {
    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name(),
                toProjects(customerDTO.getProjects()));
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name(),
                fromProjects(customer.getProjects()));
    }

    public static Set<Customer> toCustomers(Set<CustomerDTO> customers) {
        if (customers!=null) {
            return customers.stream()
                    .map(CustomerConverter::toCustomerWithoutAssociative)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static Set<CustomerDTO> fromCustomers(Set<Customer> customers) {
        if (customers!=null){
            return customers.stream()
                    .map(CustomerConverter::fromCustomerWithoutAssociative)
                    .collect(Collectors.toSet());
        }
        return null;
    }

    public static Customer toCustomerWithoutAssociative(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name());
    }

    public static CustomerDTO fromCustomerWithoutAssociative(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name());
    }
}
