package com.FelipeLohan.portifolio.customer.dtos;

public record ResponseCustomerDTO(
        Long id,
        String name,
        String email,
        String telephoneNumber,
        String message
) {}