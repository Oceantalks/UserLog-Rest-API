package se.hidfy.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import se.hidfy.springdemo.entity.Customer;
import se.hidfy.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {
	
	// inject the customer dao
	@Autowired
	private CustomerService customerService;
	@GetMapping("/list")
	public String customerList(Model theModel) {
		
		// get customers from the service
		List<Customer> theCustomers = customerService.getCustomers();
		
		// add customer to the model
		theModel.addAttribute("customerList", theCustomers);
		
		return "customer-list";
	}
	
	@GetMapping("/showFormForCreate")
	public String showFormForAdd(Model theModel) {
		
		// create a new model attribute to bind data from the view
		Customer theCustomer = new Customer();
		
		// add the attribute to the model
		theModel.addAttribute("customer", theCustomer);
		
		return "create-customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String createCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		// create/save customer through CustomerServiceImpl method
		customerService.createCustomer(theCustomer);
		
		return "redirect:/customers/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		// get customer by theId
		Customer theCustomer = customerService.getCustomer(theId);
		
		// add Customer to the model attribute
		theModel.addAttribute("customer", theCustomer);
		
		return "update-customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		// delete the customer
		customerService.deleteCustomer(theId);
		
		return "redirect:/customers/list";
	}
	

}
