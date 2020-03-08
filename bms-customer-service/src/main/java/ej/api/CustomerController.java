package ej.api;

import ej.domain.Customer;
import ej.domain.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CreateConsumerResponse create(@RequestBody CreateCustomerRequest request) {
        ResultWithEvents<Customer> result = customerService.create(request.convertToEntity(request));
        return new CreateConsumerResponse(result.result.getId());
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<CustomerResponse> get(@PathVariable long customerId) {
        return customerService.findById(customerId)
                .map(consumer -> new ResponseEntity<>(CustomerResponse.convertToDTO(consumer), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
