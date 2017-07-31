package se.hidfy.springdemo.service;

import java.util.List;

import se.hidfy.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	public void createCustomer(Customer customer);
	
	public Customer getCustomer(int id);

	public void deleteCustomer(int theId);

}
