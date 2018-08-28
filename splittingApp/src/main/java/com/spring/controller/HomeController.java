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
	TransactionService transactionService;
	
	@Autowired
	CustomerService custService;
	
	
	@GetMapping( value = "/Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Transaction>> listAllTransactions() {
		List<Transaction> transactions = transactionService.findAllTransactions();
		if (transactions.isEmpty()) {
			return new ResponseEntity<List<Transaction>>(HttpStatus.NO_CONTENT);// You many decide to return																				// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);
	}

	@GetMapping(value = "/Transaction/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> getTransaction(@PathVariable("id") int id) {
		Transaction transaction = transactionService.findById(id);
		if (transaction == null) {
			System.out.println("Transaction with id " + id + " not found");
			return new ResponseEntity<Transaction>(HttpStatus.NOT_FOUND);
		}
		System.out.println("TransactionId found :" +transaction.getTransactionId());

		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
	}

	@PostMapping(value = "/Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> createTransaction(HttpServletRequest req) {
		try {
		int payeeId = Integer.valueOf(req.getParameter("payeeId"));
		int beneficiaryId = Integer.valueOf(req.getParameter("beneficiaryId"));
		int amount = Integer.valueOf(req.getParameter("amount"));
		int noOfSplitters = Integer.valueOf(req.getParameter("noOfSplitters"));
		Transaction transaction = new Transaction(payeeId,  beneficiaryId,  amount,  noOfSplitters);
		transactionService.saveTransaction(transaction);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.CREATED);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Transaction>( HttpStatus.BAD_REQUEST);
		}
		
	}
	
//	---------------------------------Customer work starts from below ---------------------------------------
	
	@RequestMapping(value = "/CustomerRegister", method = RequestMethod.POST)
	public ResponseEntity<Customer> createCustomer(HttpServletRequest request) {
		
		Customer cust = new Customer(request.getParameter("name"),request.getParameter("password"),request.getParameter("phone"));
		custService.saveCustomer(cust);
		return new ResponseEntity<Customer>(cust,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = { "/CustomerLogin" }, method = RequestMethod.POST)
	public ResponseEntity<Customer> processLoginHtmlCredentials(HttpServletRequest req) {
		String number = req.getParameter("phone");
		String password = req.getParameter("password");
			List<Customer> customerList = custService.findByNumberAndPassword(number,password);
			if(customerList.size()==1) {
				return new ResponseEntity<Customer>(customerList.get(0), HttpStatus.OK);
			}
		
		return new ResponseEntity<Customer>(HttpStatus.CONFLICT);
	}
	
	
	
	
	
	
	
}
