package com.insight.cdp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insight.cdp.controllerInterface.ExpenseControllerInterface;
import com.insight.cdp.model.Expense;
import com.insight.cdp.payload.request.ExpenseRequest;
import com.insight.cdp.payload.response.ExpenseResponse;
import com.insight.cdp.service.ExpenseService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class ExpenseController implements ExpenseControllerInterface {

	private ExpenseService service;
	
	public ExpenseController(ExpenseService service) {
		this.service = service;
	}
	
	@PostMapping("/create-expense")
	public ExpenseResponse createExpense(@RequestBody ExpenseRequest expenseRequest) {
		return service.createExpense(expenseRequest);
	}
	
	@GetMapping("/get-all-expense")
	public List<ExpenseResponse> getAllExpense(){
		return service.getAllExpense();
	}
	
	@GetMapping("/get-expense-by-id/{id}")
	public ExpenseResponse getExpenseByid(@PathVariable long id, HttpServletRequest request) {
		return service.getExpenseByid(id, request);
	}
	
	@PutMapping("/update-expense")
	public ExpenseResponse updateExpense(@RequestBody ExpenseRequest expenseRequest, HttpServletRequest request) {
		return service.updateExpense(expenseRequest, request);
	}
	
	@DeleteMapping("/delete-expense-by-id/{id}")
	public void deleteExpense(@PathVariable long id, HttpServletRequest request) {
		service.deleteExpense(id, request);
	}
	
	@GetMapping("/get-expense-by-user/{userId}")
	public List<Expense> getExpenseByUser(@PathVariable long userId) {
		return service.getExpenseByUser(userId);
	}
}
