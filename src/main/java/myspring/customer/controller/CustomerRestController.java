package myspring.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import myspring.customer.service.CustomerService;
import myspring.customer.vo.CustomerVO;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
	@Autowired
	private CustomerService customerService;

	@GetMapping  
	public List<CustomerVO> getUserList() {
		List<CustomerVO> custList = customerService.getAllCustomerList();
		return custList;
	}
	
	@GetMapping("/{email}/")
	public CustomerVO getUser(@PathVariable String email) {
		CustomerVO customer = customerService.getCustomerInfoByEmail(email);
		return customer;
	}
		
	@PostMapping
	public CustomerVO insertUser(@RequestBody CustomerVO customer) {
		if (customer != null) {
			customerService.insertCustomer(customer);
			return customerService.getCustomerInfoByEmail(customer.getEmail());
		} else {	
			return null;
		}
	}
		
}
