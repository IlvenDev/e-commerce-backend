package pl.ilvendev.ecommerce.customer;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO);
        Customer createdCustomer = customerRepository.save(customer);
        return CustomerMapper.mapToDTO(createdCustomer);
    }

    public CustomerDTO findCustomerById(Long customerId) {
        Customer fetchedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("A customer with ID " + customerId + " was not found."));
        return CustomerMapper.mapToDTO(fetchedCustomer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map((customer -> CustomerMapper.mapToDTO(customer)))
                .collect(Collectors.toList());
    }

    public CustomerDTO updateCustomer(Long customerId, CustomerDTO customerDTO) {
        Customer fetchedCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("A customer with ID " + customerId + " was not found."));

        fetchedCustomer.setFirstName(customerDTO.getFirstName());
        fetchedCustomer.setLastName(customerDTO.getLastName());
        fetchedCustomer.setEmail(customerDTO.getEmail());
        fetchedCustomer.setPhone(customerDTO.getPhone());
        fetchedCustomer.setAddress(customerDTO.getAddress());
        fetchedCustomer.setCity(customerDTO.getCity());
        fetchedCustomer.setCountry(customerDTO.getCountry());
        Customer updatedCustomer = customerRepository.save(fetchedCustomer);

        return CustomerMapper.mapToDTO(updatedCustomer);
    }

    public void deleteCustomer(Long customerId) {
        if(!customerRepository.existsById(customerId)) {
            throw new CustomerNotFoundException("A customer with ID " + customerId + " was not found.");
        }
        customerRepository.deleteById(customerId);
    }
}
