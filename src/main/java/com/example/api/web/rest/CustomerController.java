package com.example.api.web.rest;

import java.util.List;

import com.example.api.form.CustomerForm;
import com.example.api.form.EnderecoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@PostMapping
	public void create(final CustomerForm form) {
		this.service.create(form);
	}

	@PutMapping
	public void put(final CustomerForm form) {
		this.service.put(form);
	}

	@DeleteMapping
	@ResponseBody
	public void delete(final Long id) {
		this.service.delete(id);
	}

	@PostMapping("/endereco")
	public void cadastrarEndereco(final EnderecoForm form) {
		this.service.cadastrarEndereco(form);
	}
}
