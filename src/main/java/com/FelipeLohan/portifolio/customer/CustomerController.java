package com.FelipeLohan.portifolio.customer;

import com.FelipeLohan.portifolio.customer.dtos.RequestCustomerDTO;
import com.FelipeLohan.portifolio.customer.dtos.ResponseCustomerDTO;
import com.FelipeLohan.portifolio.customer.interfaces.CustomerControllerI;
import com.FelipeLohan.portifolio.customer.interfaces.CustomerServiceI;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController implements CustomerControllerI {

    private final CustomerServiceI customerService;

    public CustomerController(CustomerServiceI customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResponseCustomerDTO> create(@RequestBody @Valid RequestCustomerDTO dto) {
        ResponseCustomerDTO response = customerService.create(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ResponseCustomerDTO>> findAll() {
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCustomerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseCustomerDTO> update(@PathVariable Long id,
                                                      @RequestBody @Valid RequestCustomerDTO dto) {
        return ResponseEntity.ok(customerService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}