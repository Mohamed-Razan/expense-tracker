package com.insight.cdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insight.cdp.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
