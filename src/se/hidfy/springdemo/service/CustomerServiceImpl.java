package se.hidfy.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import se.hidfy.springdemo.dao.CustomerDAO;
import se.hidfy.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

	@Override
	@Transactional
	public void createCustomer(Customer theCustomer) {
		customerDAO.createCustomer(theCustomer);	
	}

	@Override
	@Transactional
	public Customer getCustomer(int id) {
		Customer theCustomer = customerDAO.getCustomer(id);
		return theCustomer;
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		customerDAO.deleteCustomer(theId);		
	}
	
	

}
