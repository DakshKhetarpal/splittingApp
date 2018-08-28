package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.model.Customer;
import com.spring.model.Login;
import com.spring.model.Transaction;
import com.spring.service.CustomerService;
import com.spring.service.TransactionService;

@RestController
@SessionAttributes("userInSession")
public class HomeController {

	@Autowired
	TransactionService userService;

	@Autowired
	CustomerService custService;

	@GetMapping("/getAll")
	public ResponseEntity<List<Transaction>> listAllUsers() {
		List<Transaction> users = userService.findAllTransactions();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Transaction>>(HttpStatus.NO_CONTENT);// You many decide to return																				// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Transaction>>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> getUser(@PathVariable("id") int id) {

		Transaction user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Transaction>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/mpay", method = RequestMethod.POST)
	public ResponseEntity<Void> createTransaction(@RequestBody Transaction user, UriComponentsBuilder ucBuilder,HttpServletRequest req) {
		userService.saveTransaction(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/transaction/{id}").buildAndExpand(user.getTransactionId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

//	---------------------------------Customer work starts from below ---------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> createCustomer(@RequestBody Customer cust, UriComponentsBuilder ucBuilder,HttpServletRequest req) {
		custService.saveCustomer(cust);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(cust.getCustomerId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ResponseEntity<Void> processLoginHtmlCredentials(@RequestBody Login login,UriComponentsBuilder ucBuilder,
		HttpServletRequest req, Model model) {

		Customer cust = new Customer();
		cust.setName(login.getName());
		System.out.println(login.getName());
		System.out.println(login.getPassword());
		System.out.println("some testing on github");
		cust.setPassword(login.getPassword());

		if (custService.findCustomer(cust)) {
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(cust.getCustomerId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);


		}

		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}













	
}
