package com.example.customer.respository;


import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "INSERT INTO customers (firstName, lastName) VALUES (?,?)";

    @Override
    public void add(Customer customer) {
        jdbcTemplate.update(INSERT_SQL, customer.getFirstname(), customer.getLastname());
    }

    private final String SELECT_BY_ID_SQL = "SELECT * FROM customers WHERE id = ?";

    @Override
    public Customer getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new PersonMapper(), id);
    }

    private final String SELECT_SQL = "SELECT * FROM persons";

    @Override
    public List<Customer> get() {
        return jdbcTemplate.query(SELECT_SQL, new PersonMapper());
    }

    private final String UPDATE_SQL = "UPDATE customers SET firstName=?, lastName=? where id=?";

    @Override
    public void update(Customer customer) {
        jdbcTemplate.update(UPDATE_SQL, customer.getFirstname(), customer.getLastname(), customer.getId());
    }

    private final String DELETE_SQL = "DELETE FROM customers WHERE id=?";

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }


    // Map a row of the result set to a person object
    private static class PersonMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstname(rs.getString("firstName"));
            customer.setLastname(rs.getString("lastName"));
            return customer;
        }

    }

}
