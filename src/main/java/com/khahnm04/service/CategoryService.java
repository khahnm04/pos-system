package com.khahnm04.service;

import com.khahnm04.exception.UserException;
import com.khahnm04.payload.dto.CategoryDTO;
import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO categoryDTO) throws Exception;
    List<CategoryDTO> getCategoryByStore(Long storeId);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
