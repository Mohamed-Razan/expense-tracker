package com.insight.cdp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insight.cdp.controllerInterface.CategoryControllerInterface;
import com.insight.cdp.payload.request.CategoryRequest;
import com.insight.cdp.payload.response.CategoryResponse;
import com.insight.cdp.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CategoryController implements CategoryControllerInterface {

	private CategoryService service;
	
	public CategoryController(CategoryService service) {
		this.service = service;
	}
	
	@PostMapping("/create-category")
	public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest) {
		return service.createCategory(categoryRequest);
	}
	
	@GetMapping("/get-all-category")
	public List<CategoryResponse> getAllCategories(){
		return service.getAllCategories();
	}
	
	@GetMapping("/get-category-by-id/{id}")
	public CategoryResponse getCategoryById(@PathVariable long id) {
		return service.getCategoryById(id);
	}
	
	@PutMapping("/update-category")
	public CategoryResponse updateCategory(@RequestBody CategoryRequest categoryRequest) {
		return service.updateCategory(categoryRequest);
	}
	
	@DeleteMapping("/delete-category-by-id/{id}")
	public void deleteCategoryById(@PathVariable long id) {
		service.deleteCategoryById(id);
	}
}
