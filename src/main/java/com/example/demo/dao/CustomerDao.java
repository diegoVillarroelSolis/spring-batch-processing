package com.example.demo.dao;

import com.example.demo.domain.Customer;

public interface CustomerDao {
    Customer findCustomerByTaxId(String taxId);
}
