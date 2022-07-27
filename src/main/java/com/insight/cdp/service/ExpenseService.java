package com.insight.cdp.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.insight.cdp.model.Expense;import com.insight.cdp.model.User;
import com.insight.cdp.payload.request.ExpenseRequest;
import com.insight.cdp.payload.response.ExpenseResponse;
import com.insight.cdp.repository.CategoryRepository;
import com.insight.cdp.repository.ExpenseRepository;
import com.insight.cdp.security.jwt.AuthTokenFilter;
import com.insight.cdp.security.jwt.JwtUtils;
import com.insight.cdp.serviceInterface.ExpenseServiceInterface;

@Service
public class ExpenseService implements ExpenseServiceInterface {

	private ExpenseRepository repository;
	
	private UserService userService;
	
	private CategoryRepository categoryRepository;
	
	private AuthTokenFilter authTokenFilter;
	
	private JwtUtils jwtUtils;
	
	public ExpenseService(ExpenseRepository repository, CategoryRepository categoryRepository, UserService userService,
			JwtUtils jwtUtils, AuthTokenFilter authTokenFilter) {
		this.repository = repository;
		this.userService = userService;
		this.categoryRepository = categoryRepository;
		this.jwtUtils = jwtUtils;
		this.authTokenFilter = authTokenFilter;
	}
	
	public ExpenseResponse createExpense(ExpenseRequest expenseRequest) {
		Expense expense = new Expense(expenseRequest.getDescription(), expenseRequest.getExpenseDate(), expenseRequest.getPrice(), 
				categoryRepository.findById(expenseRequest.getCategoryId()).orElse(null), userService.getUserById(expenseRequest.getUserId()));
//		Expense expense = new Expense();
//		expense.setCategory(categoryRepository.findById(expenseRequest.getCategoryId()).orElse(null));
		
		repository.save(expense);
		
		ExpenseResponse expenseResponse = new ExpenseResponse(expense.getId(), expense.getDescription(), expense.getExpenseDate(), expense.getPrice(), 
				expense.getCategory(), expense.getUser());
		
		return expenseResponse;
	}
	
	public List<ExpenseResponse> getAllExpense(){
		List<Expense> expenses = repository.findAll();
		List<ExpenseResponse> expenseResponses = new ArrayList<>();
		
		for(Expense expense: expenses) {
			ExpenseResponse expenseResponse = new ExpenseResponse(expense.getId(), expense.getDescription(), expense.getExpenseDate(), expense.getPrice(), 
					expense.getCategory(), expense.getUser());
			expenseResponses.add(expenseResponse);
		}
		return expenseResponses;
	}
	
	public ExpenseResponse getExpenseByid(long id, HttpServletRequest request) {
		Expense expense = repository.findById(id).orElse(null);
		String jwt = authTokenFilter.parseJwt(request);
		String useremail = jwtUtils.getEmailFromJwtToken(jwt);
		
		ExpenseResponse expenseResponse = new ExpenseResponse(expense.getId(), expense.getDescription(), expense.getExpenseDate(), expense.getPrice(), 
				expense.getCategory(), expense.getUser());
		if(useremail.equals(expenseResponse.getUser().getEmail()) || request.isUserInRole("ROLE_ADMIN")) {
			return expenseResponse;
		}
		return null;
	}
	
	public ExpenseResponse updateExpense(ExpenseRequest expenseRequest, HttpServletRequest request) {
		
		String jwt = authTokenFilter.parseJwt(request);
		String useremail = jwtUtils.getEmailFromJwtToken(jwt);
		
		try {
			Expense expense = repository.findById(expenseRequest.getId()).orElse(null);
			if(useremail.equals(expense.getUser().getEmail()) || request.isUserInRole("ROLE_ADMIN")) {
				expense.setCategory(categoryRepository.findById(expenseRequest.getCategoryId()).orElse(null));
				expense.setDescription(expenseRequest.getDescription());
				expense.setExpenseDate(expenseRequest.getExpenseDate());
				expense.setPrice(expenseRequest.getPrice());
				expense.setUser(userService.getUserById(expenseRequest.getUserId()));
				repository.save(expense);
				
				ExpenseResponse expenseResponse = new ExpenseResponse(expense.getId(), expense.getDescription(), expense.getExpenseDate(), expense.getPrice(), 
						expense.getCategory(), expense.getUser());
				
				return expenseResponse;
			}
			else {
				return null;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public void deleteExpense(long id, HttpServletRequest request) {
		String jwt = authTokenFilter.parseJwt(request);
		String useremail = jwtUtils.getEmailFromJwtToken(jwt);
		Expense expense = repository.findById(id).orElse(null);
		if(useremail.equals(expense.getUser().getEmail()) || request.isUserInRole("ROLE_ADMIN")) {
			repository.deleteById(id);
		}
	}
	
	public List<Expense> getExpenseByUser(long userId) {
		User user = userService.getUserById(userId);
		return repository.findByUser(user);
	}
}
