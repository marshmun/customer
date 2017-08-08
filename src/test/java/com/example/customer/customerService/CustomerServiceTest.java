package com.example.customer.customerService;


import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.service.CustomerService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

    @Before

    @After


    @Test
    public void testAddGet() {
        // Get unique names every time this test runs
        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());
        String phone = Long.toString(System.currentTimeMillis());
        String email = Long.toString(System.currentTimeMillis());

        Customer customer1 = new Customer();
        customer1.setFirstname(firstName);
        customer1.setLastname(lastName);
        customer1.setPhone(phone);
        customer1.setEmail(email);
        customerService.add(customer1);

        List<Customer> customer = customerService.getAll();

        Customer customer2 = findInList(customer, firstName, lastName, phone, email);
        Assert.assertNotNull(customer2);

        Customer customer3 = customerService.getById(customer2.getId());
        Assert.assertNotNull(customer3);
        Assert.assertEquals(firstName, customer3.getFirstname());
        Assert.assertEquals(lastName, customer3.getLastname());
    }

    @Test
    public void testUpdate() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> customer = customerService.getAll();

        Customer customer2 = findInList(customer, customer1.getFirstname(), customer1.getLastname(), customer1.getPhone(), customer1.getEmail());
        Assert.assertNotNull(customer2);

        String updateFirstName = Long.toString(System.currentTimeMillis());
        String updateLastName = Long.toString(System.currentTimeMillis());
        String updatePhone = Long.toString(System.currentTimeMillis());
        String updateEmail = Long.toString(System.currentTimeMillis());

        customer2.setFirstname(updateFirstName);
        customer2.setLastname(updateLastName);
        customer2.setPhone(updatePhone);
        customer2.setEmail(updateEmail);
        customerService.update(customer2);

        customer = customerService.getAll();

        Customer customer3 = findInList(customer, updateFirstName, updateLastName, updatePhone, updateEmail);
        Assert.assertNotNull(customer3);
        Assert.assertEquals(customer2.getId(), customer3.getId());
    }



    @Test
    public void testDelete() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> customer = customerService.getAll();

        Customer customer2 = findInList(customer, customer1.getFirstname(), customer1.getLastname(), customer1.getPhone(),customer1.getEmail());
        Assert.assertNotNull(customer2);

        customerService.delete(customer2.getId());

        customer = customerService.getAll();
        Customer customer3 = findInList(customer, customer1.getFirstname(), customer1.getLastname(), customer1.getPhone(), customer1.getEmail());
        Assert.assertNull(customer3);
    }

    }

