package com.enterprisepro.erp.service.impl;

import com.enterprisepro.erp.dto.CustomerDto;
import com.enterprisepro.erp.entity.Customer;
import com.enterprisepro.erp.exception.BadRequestException;
import com.enterprisepro.erp.exception.ResourceNotFoundException;
import com.enterprisepro.erp.repository.CustomerRepository;
import com.enterprisepro.erp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDto> getAllCustomers(String search, Pageable pageable) {
        if (StringUtils.hasText(search)) {
            return customerRepository.findByNameContainingIgnoreCaseOrCustomerCodeContainingIgnoreCaseOrCompanyContainingIgnoreCaseOrEmailContainingIgnoreCase(
                    search, search, search, search, pageable).map(this::mapToDto);
        }
        return customerRepository.findAll(pageable).map(this::mapToDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerDto> getActiveCustomers() {
        return customerRepository.findByActiveTrue().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDto(c);
    }

    @Override
    @Transactional
    public CustomerDto createCustomer(CustomerDto dto) {
        if (customerRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Customer with email '" + dto.getEmail() + "' already exists!");
        }

        Customer c = new Customer();
        c.setCustomerCode(StringUtils.hasText(dto.getCustomerCode()) ? dto.getCustomerCode() : "CUST-" + (1000 + customerRepository.count() + 1));
        c.setName(dto.getName());
        c.setCompany(dto.getCompany());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setAddress(dto.getAddress());
        c.setCity(dto.getCity());
        c.setCountry(dto.getCountry());
        c.setTaxNumber(dto.getTaxNumber());
        c.setCustomerType(dto.getCustomerType() != null ? dto.getCustomerType() : "CORPORATE");
        c.setCreditStatus(dto.getCreditStatus() != null ? dto.getCreditStatus() : "GOOD");
        c.setTotalSpend(0.0);
        c.setActive(true);

        Customer saved = customerRepository.save(c);
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public CustomerDto updateCustomer(Long id, CustomerDto dto) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        c.setName(dto.getName());
        c.setCompany(dto.getCompany());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        c.setAddress(dto.getAddress());
        c.setCity(dto.getCity());
        c.setCountry(dto.getCountry());
        c.setTaxNumber(dto.getTaxNumber());
        c.setCustomerType(dto.getCustomerType());
        c.setCreditStatus(dto.getCreditStatus());
        c.setActive(dto.isActive());

        Customer updated = customerRepository.save(c);
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        c.setActive(false);
        customerRepository.save(c);
    }

    private CustomerDto mapToDto(Customer c) {
        CustomerDto dto = new CustomerDto();
        dto.setId(c.getId());
        dto.setCustomerCode(c.getCustomerCode());
        dto.setName(c.getName());
        dto.setCompany(c.getCompany());
        dto.setEmail(c.getEmail());
        dto.setPhone(c.getPhone());
        dto.setAddress(c.getAddress());
        dto.setCity(c.getCity());
        dto.setCountry(c.getCountry());
        dto.setTaxNumber(c.getTaxNumber());
        dto.setCustomerType(c.getCustomerType());
        dto.setCreditStatus(c.getCreditStatus());
        dto.setTotalSpend(c.getTotalSpend());
        dto.setActive(c.isActive());
        return dto;
    }
}
