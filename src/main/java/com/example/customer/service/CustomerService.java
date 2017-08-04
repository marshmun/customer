package com.example.customer.service;

import com.example.customer.model.Customer;


import java.util.List;

public interface CustomerService {
    void add(Customer customer);

    void update(Customer customer);

    Customer getById(int id);

    List<Customer> getAll();

    void delete(int id);
}