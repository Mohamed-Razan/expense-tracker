package com.insight.cdp.payload.request;

import java.sql.Date;

public class ExpenseRequest {

	private long id;
	private String description;
	private Date expenseDate;
	private double price;
	private long userId;
	private long categoryId;

	public ExpenseRequest() {
		super();
	}

	public ExpenseRequest(String description, Date expenseDate, double price, long userId, long categoryId) {
		super();
		this.description = description;
		this.expenseDate = expenseDate;
		this.price = price;
		this.userId = userId;
		this.categoryId = categoryId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

}
