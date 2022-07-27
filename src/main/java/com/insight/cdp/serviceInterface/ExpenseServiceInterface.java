package com.insight.cdp.serviceInterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.insight.cdp.model.Expense;
import com.insight.cdp.payload.request.ExpenseRequest;
import com.insight.cdp.payload.response.ExpenseResponse;

public interface ExpenseServiceInterface {
	
	public ExpenseResponse createExpense(ExpenseRequest expenseRequest);
	
	public List<ExpenseResponse> getAllExpense();
	
	public ExpenseResponse getExpenseByid(long id, HttpServletRequest request);
	
	public ExpenseResponse updateExpense(ExpenseRequest expenseRequest, HttpServletRequest request);
	
	public void deleteExpense(long id, HttpServletRequest request);
	
	public List<Expense> getExpenseByUser(long userId);
}
