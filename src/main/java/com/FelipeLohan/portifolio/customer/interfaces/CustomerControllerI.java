package com.FelipeLohan.portifolio.customer.interfaces;

import com.FelipeLohan.portifolio.customer.dtos.RequestCustomerDTO;
import com.FelipeLohan.portifolio.customer.dtos.ResponseCustomerDTO;
import com.FelipeLohan.portifolio.exception.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Customers", description = "Endpoints for managing customers")
public interface CustomerControllerI {

    @Operation(summary = "Create a new customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully",
                    content = @Content(schema = @Schema(implementation = ResponseCustomerDTO.class))),
            @ApiResponse(responseCode = "422", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<ResponseCustomerDTO> create(@RequestBody @Valid RequestCustomerDTO dto);

    @Operation(summary = "List all customers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customers listed successfully",
                    content = @Content(schema = @Schema(implementation = ResponseCustomerDTO.class)))
    })
    ResponseEntity<List<ResponseCustomerDTO>> findAll();

    @Operation(summary = "Find a customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found",
                    content = @Content(schema = @Schema(implementation = ResponseCustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<ResponseCustomerDTO> findById(@PathVariable Long id);

    @Operation(summary = "Update a customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully",
                    content = @Content(schema = @Schema(implementation = ResponseCustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))),
            @ApiResponse(responseCode = "422", description = "Validation error",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<ResponseCustomerDTO> update(@PathVariable Long id, @RequestBody @Valid RequestCustomerDTO dto);

    @Operation(summary = "Delete a customer by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Customer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    ResponseEntity<Void> delete(@PathVariable Long id);
}