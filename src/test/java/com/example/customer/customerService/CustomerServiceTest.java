package com.example.customer.customerService;


import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.example.customer.common.CustomerServiceTestUtils.createTestCustomer;
import static com.example.customer.common.CustomerServiceTestUtils.findInList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    CustomerService customerService;

    @Test
    public void testAddGet() {
        Customer testCustomer1 = createTestCustomer();  // Create test customer object
        customerService.add(testCustomer1);  // Add to database

        // Verify get all
        List<Customer> customers = customerService.getAll();// Get all records from database
        Assert.assertNotNull("should return customers from db", customers);
        // Verify add
        Customer foundTestCustomer1 = findInList(customers, testCustomer1.getFirstname(), testCustomer1.getLastname(),
                testCustomer1.getPhone(), testCustomer1.getEmail());
        Assert.assertNotNull("should find added customer in customers returned from db", foundTestCustomer1);

        int id = foundTestCustomer1.getId();
        foundTestCustomer1 = null; // Reset
        // Verify get by id
        foundTestCustomer1 = customerService.getById(id);
        Assert.assertNotNull("should return customer from db", foundTestCustomer1);
        Assert.assertEquals("first name should match", testCustomer1.getFirstname(),
                foundTestCustomer1.getFirstname());
        Assert.assertEquals("last name should match", testCustomer1.getLastname(),
                foundTestCustomer1.getLastname());
        Assert.assertEquals("phone should match", testCustomer1.getPhone(), foundTestCustomer1.getPhone());
        Assert.assertEquals("email name should match", testCustomer1.getEmail(), foundTestCustomer1.getEmail());
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testDelete() {

    }
}