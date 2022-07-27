package com.insight.cdp.payload.response;

import java.sql.Date;

import com.insight.cdp.model.Category;
import com.insight.cdp.model.User;

public class ExpenseResponse {

	private Long id;
	private String description;
	private Date expenseDate;
	private double price;
	private Category category;
	private User user;

	public ExpenseResponse(Long id, String description, Date expenseDate, double price, Category category, User user) {
		super();
		this.id = id;
		this.description = description;
		this.expenseDate = expenseDate;
		this.price = price;
		this.category = category;
		this.user = user;
	}

	public ExpenseResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
