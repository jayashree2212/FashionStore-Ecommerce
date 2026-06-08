package com.fashionstore.dao;

import com.fashionstore.model.Category;
import java.util.List;

public interface CategoryDAO {

    boolean addCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(int categoryId);

    boolean updateCategory(Category category);

    boolean deleteCategory(int categoryId);
}