package com.insight.cdp.controllerInterface;

import java.util.List;

import com.insight.cdp.payload.request.CategoryRequest;
import com.insight.cdp.payload.response.CategoryResponse;

public interface CategoryControllerInterface {
	
	public CategoryResponse createCategory(CategoryRequest categoryRequest);
	
	public List<CategoryResponse> getAllCategories();
	
	public CategoryResponse getCategoryById(long id);
	
	public CategoryResponse updateCategory(CategoryRequest categoryRequest);
	
	public void deleteCategoryById(long id);
}
