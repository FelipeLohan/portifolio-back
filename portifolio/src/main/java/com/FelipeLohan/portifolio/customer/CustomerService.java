package com.FelipeLohan.portifolio.customer;

import com.FelipeLohan.portifolio.customer.dtos.RequestCustomerDTO;
import com.FelipeLohan.portifolio.customer.dtos.ResponseCustomerDTO;
import com.FelipeLohan.portifolio.customer.interfaces.CustomerServiceI;
import com.FelipeLohan.portifolio.customer.mapper.CustomerMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService implements CustomerServiceI {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public ResponseCustomerDTO create(RequestCustomerDTO dto) {
        Customer customer = customerMapper.toEntity(dto);
        Customer saved = customerRepository.save(customer);
        return customerMapper.toResponseDTO(saved);
    }

    @Transactional(readOnly = true)
    public List<ResponseCustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResponseCustomerDTO findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        return customerMapper.toResponseDTO(customer);
    }

    @Transactional
    public ResponseCustomerDTO update(Long id, RequestCustomerDTO dto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + id));
        customerMapper.updateEntityFromDTO(dto, customer);
        return customerMapper.toResponseDTO(customer);
    }

    @Transactional
    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new EntityNotFoundException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }
}