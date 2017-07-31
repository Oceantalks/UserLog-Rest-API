package se.hidfy.springdemo.dao;

import java.util.List;

import se.hidfy.springdemo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();
	
	public void createCustomer(Customer thCustomer);
	
	public Customer getCustomer(int id);

	public void deleteCustomer(int theId);
		
}
