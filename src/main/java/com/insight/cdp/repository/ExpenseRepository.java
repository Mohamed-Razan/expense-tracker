package com.insight.cdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insight.cdp.model.Expense;
import com.insight.cdp.model.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{
	
	List<Expense> findByUser(User user);

}
