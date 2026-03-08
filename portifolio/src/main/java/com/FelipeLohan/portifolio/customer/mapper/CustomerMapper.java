package com.FelipeLohan.portifolio.customer.mapper;

import com.FelipeLohan.portifolio.customer.Customer;
import com.FelipeLohan.portifolio.customer.dtos.RequestCustomerDTO;
import com.FelipeLohan.portifolio.customer.dtos.ResponseCustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(RequestCustomerDTO dto);

    ResponseCustomerDTO toResponseDTO(Customer customer);

    void updateEntityFromDTO(RequestCustomerDTO dto, @MappingTarget Customer customer);
}