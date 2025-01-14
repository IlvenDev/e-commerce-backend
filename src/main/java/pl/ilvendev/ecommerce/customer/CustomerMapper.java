package pl.ilvendev.ecommerce.customer;

public class CustomerMapper {
    public static CustomerDTO mapToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getZip(),
                customer.getCountry()
        );
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getId(),
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getEmail(),
                customerDTO.getPhone(),
                customerDTO.getAddress(),
                customerDTO.getCity(),
                customerDTO.getState(),
                customerDTO.getZip(),
                customerDTO.getCountry()
        );
    }
}
