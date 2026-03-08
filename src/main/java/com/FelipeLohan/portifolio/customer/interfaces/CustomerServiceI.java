package com.FelipeLohan.portifolio.customer.interfaces;

import com.FelipeLohan.portifolio.customer.dtos.RequestCustomerDTO;
import com.FelipeLohan.portifolio.customer.dtos.ResponseCustomerDTO;

import java.util.List;

public interface CustomerServiceI {

    ResponseCustomerDTO create(RequestCustomerDTO dto);

    List<ResponseCustomerDTO> findAll();

    ResponseCustomerDTO findById(Long id);

    ResponseCustomerDTO update(Long id, RequestCustomerDTO dto);

    void delete(Long id);
}