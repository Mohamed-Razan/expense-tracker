package com.insight.cdp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.insight.cdp.model.Category;
import com.insight.cdp.payload.request.CategoryRequest;
import com.insight.cdp.payload.response.CategoryResponse;
import com.insight.cdp.repository.CategoryRepository;
import com.insight.cdp.serviceInterface.CategoryServiceInterface;

@Service
public class CategoryService implements CategoryServiceInterface {

	private CategoryRepository repository;
	
	public CategoryService(CategoryRepository repository) {
		this.repository = repository;
	}
	
	public CategoryResponse createCategory(CategoryRequest categoryRequest) {
		Category category = new Category(categoryRequest.getName());
		repository.save(category);
		
		CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getName());
		return categoryResponse;
	}
	
	public List<CategoryResponse> getAllCategories(){
		List<Category> categoryList = repository.findAll();
		List<CategoryResponse> categoryResponseList = new ArrayList<>();
		
		for(Category category: categoryList) {
			CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getName());
			categoryResponseList.add(categoryResponse);
		}
		return categoryResponseList;
	}
	
	public CategoryResponse getCategoryById(long id) {
		Category category = repository.findById(id).orElse(null);
		CategoryResponse categoryResponse = new CategoryResponse(category.getId(), category.getName());
		return categoryResponse;
	}
	
	public CategoryResponse updateCategory(CategoryRequest categoryRequest) {
		
		try {
			Category existingCategory = repository.findById(categoryRequest.getId()).orElse(null);
			existingCategory.setName(categoryRequest.getName());
			repository.save(existingCategory);
			
			CategoryResponse categoryResponse = new CategoryResponse(existingCategory.getId(), existingCategory.getName());
			return categoryResponse;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public void deleteCategoryById(long id) {
		repository.deleteById(id);
	}
}
